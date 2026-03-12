const API_URL = '/api/ktp';

let isEditing = false;

// Format tanggal dari YYYY-MM-DD menjadi DD-MM-YYYY untuk display
/**
 * Format a date string into a localized Indonesian display format.
 * @param {string} dateString - The date string from the API (YYYY-MM-DD).
 * @returns {string} The localized date string.
 */
function formatDateForDisplay(dateString) {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('id-ID', options);
}

$(document).ready(function () {
    // Load data awal
    fetchKtpData();

    // Handle submit form (Create & Update)
    $('#ktpForm').on('submit', function (e) {
        e.preventDefault();
        saveKtpData();
    });
});

// GET: Mengambil seluruh data KTP
/**
 * Fetch all KTP data from the REST API and render the table.
 */
function fetchKtpData() {
    $.ajax({
        url: API_URL,
        type: 'GET',
        success: function (response) {
            renderTable(response);
        },
        error: function (err) {
            showAlert('Gagal memuat data KTP: ' + (err.responseJSON ? err.responseJSON.error : 'Server Error'), 'error');
        }
    });
}

/**
 * Render the KTP data array into the HTML table body.
 * @param {Array} data - Array of KTP objects.
 */
function renderTable(data) {
    const tbody = $('#ktpTable tbody');
    tbody.empty();

    if (data.length === 0) {
        tbody.append('<tr><td colspan="7" class="text-center">Tidak ada data KTP</td></tr>');
        return;
    }

    data.forEach(function (ktp, index) {
        tbody.append(createTableRow(ktp, index));
    });
}

/**
 * Create a table row HTML string for a KTP object.
 * @param {Object} ktp - The KTP object.
 * @param {number} index - The row index.
 * @returns {string} The HTML string for the table row.
 */
function createTableRow(ktp, index) {
    return `
        <tr>
            <td>${index + 1}</td>
            <td class="nomor-ktp"><strong>${ktp.nomorKtp}</strong></td>
            <td>${ktp.namaLengkap}</td>
            <td>${ktp.alamat}</td>
            <td>${formatDateForDisplay(ktp.tanggalLahir)}</td>
            <td>${ktp.jenisKelamin}</td>
            <td class="text-center action-buttons">
                <button class="btn btn-sm btn-secondary" onclick='editKtp(${JSON.stringify(ktp)})'>Edit</button>
                <button class="btn btn-sm btn-danger" onclick="deleteKtp(${ktp.id})">Hapus</button>
            </td>
        </tr>
    `;
}

// POST & PUT: Menyimpan atau update data
/**
 * Save current form data to the API (handles both Create and Update).
 */
function saveKtpData() {
    const id = $('#id').val();
    const dataKtp = {
        id: id || null,
        nomorKtp: $('#nomorKtp').val(),
        namaLengkap: $('#namaLengkap').val(),
        alamat: $('#alamat').val(),
        tanggalLahir: $('#tanggalLahir').val(),
        jenisKelamin: $('#jenisKelamin').val()
    };

    const type = isEditing ? 'PUT' : 'POST';
    const url = isEditing ? `${API_URL}/${id}` : API_URL;

    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(dataKtp),
        success: function (response) {
            const msg = isEditing ? 'Data KTP berhasil diperbarui!' : 'Data KTP berhasil ditambahkan!';
            showAlert(msg, 'success');
            resetForm();
            fetchKtpData();
        },
        error: function (err) {
            let errorMsg = 'Gagal menyimpan data.';
            if (err.responseJSON && err.responseJSON.error) {
                errorMsg = err.responseJSON.error;
            } else if (err.responseJSON) {
                // Handle validation errors format map
                errorMsg = Object.values(err.responseJSON).join(', ');
            }
            showAlert(errorMsg, 'error');
        }
    });
}

// SETUP VIEW: Mengisi field form untuk persiapan Update
function editKtp(ktp) {
    isEditing = true;
    $('#formTitle').text('Edit Data KTP');
    $('#btnSubmit').text('Update KTP');
    $('#btnCancel').show();

    $('#id').val(ktp.id);
    $('#nomorKtp').val(ktp.nomorKtp);
    $('#namaLengkap').val(ktp.namaLengkap);
    $('#alamat').val(ktp.alamat);
    $('#tanggalLahir').val(ktp.tanggalLahir);
    $('#jenisKelamin').val(ktp.jenisKelamin);

    // Scroll otomatis kearah form
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

// DELETE: Menghapus data KTP
function deleteKtp(id) {
    if (confirm('Apakah Anda yakin ingin menghapus data KTP ini?')) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'DELETE',
            success: function (response) {
                showAlert('Data KTP berhasil dihapus!', 'success');
                fetchKtpData(); // Refresh API Update Data
            },
            error: function (err) {
                showAlert('Gagal menghapus data KTP.', 'error');
            }
        });
    }
}

// RESET FORM UTILS
function resetForm() {
    isEditing = false;
    $('#formTitle').text('Tambah Data KTP');
    $('#btnSubmit').text('Simpan KTP');
    $('#btnCancel').hide();
    $('#ktpForm')[0].reset();
    $('#id').val('');
    closeAlert();
}

// ALERT UTILS
function showAlert(message, type) {
    const alertBox = $('#alertBox');
    const alertMessage = $('#alertMessage');

    alertBox.removeClass('alert-success alert-error');
    if (type === 'success') {
        alertBox.addClass('alert-success');
    } else {
        alertBox.addClass('alert-error');
    }

    alertMessage.text(message);
    alertBox.css("display", "flex");

    // Auto hide sesudah 5 Detik
    setTimeout(() => {
        closeAlert();
    }, 5000);
}

function closeAlert() {
    $('#alertBox').hide();
}
