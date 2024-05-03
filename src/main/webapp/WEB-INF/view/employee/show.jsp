<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
          integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>Document</title>
</head>
<body>
<div class="container mt-4">
    <div class="mt4">
        <div class="row">
            <div class="col-md-6">
                <h2> Danh sách nhân viên</h2>
            </div>
            <div class="col-md-6">
                <form action="" method="get">
                    <div class="input-group">
                        <input type="text" class="form-control" name="keyword" id="search-input"
                               placeholder="Nhập tên nhân viên">
                        <button class="btn btn-primary btn-search" type="submit">Tìm kiếm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="col-lg-12">
        <div class="row">
            <div class="col-lg-6">
                <nav>
                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" id="high-salary-tab" data-bs-toggle="tab" href="#high-salary">High
                                Salary</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="low-salary-tab" data-bs-toggle="tab" href="#low-salary">Low
                                Salary</a>
                        </li>
                    </ul>
                </nav>
            </div>

            <div class="col-lg-6 d-flex justify-content-end">
                <span class="filters">
                    <label for="department"><i class="fa-solid fa-filter"></i></label>
                    <select id="department">
                      <option value="all">Tất cả</option>
                      <option value="Phòng kế toán">Phòng kế toán</option>
                      <option value="Phòng nhân sự">Phòng nhân sự</option>
                      <option value="Phòng kỹ thuật">Phòng kỹ thuật</option>
                      <option value="Phòng marketing">Phòng marketing</option>
                      <option value="Phòng kinh doanh">Phòng kinh doanh</option>
                      <option value="Phòng sản xuất">Phòng sản xuất</option>
                      <option value="Phòng nghiên cứu">Phòng nghiên cứu</option>
                      <option value="Phòng hành chính">Phòng hành chính</option>
                      <option value="Phòng quản lý">Phòng quản lý</option>
                    </select>
                </span>
            </div>
        </div>


        <div class="tab-content">
            <div class="tab-pane fade show active" id="high-salary">
                <table class="table table-striped table-hover table-responsive-md">
                    <thead>
                    <tr>
                        <th style="width: 5%">ID</th>
                        <th style="width: 10%">Họ và tên</th>
                        <th style="width: 13%">Email</th>
                        <th style="width: 10%">Lương</th>
                        <th style="width: 10%">Ngày sinh</th>
                        <th style="width: 10%">Phòng Ban</th>
                        <th style="width: 20%">Trình độ học vấn</th>
                        <th style="width: 7%">Địa chỉ</th>
                        <th style="width: 15%">Sở Thích</th>
                    </tr>
                    </thead>
                    <tbody id="high-salary-tbody">

                    </tbody>

                </table>

                <nav>
                    <ul class="pagination" id="high-salary-pagination">
                        <!-- Pagination buttons will be added here by JavaScript -->
                    </ul>
                </nav>
            </div>
            <div class="tab-pane fade" id="low-salary">
                <table class="table table-striped table-hover table-responsive-md">
                    <thead>
                    <tr>
                        <th style="width: 5%">ID</th>
                        <th style="width: 10%">Họ và tên</th>
                        <th style="width: 13%">Email</th>
                        <th style="width: 10%">Lương</th>
                        <th style="width: 10%">Ngày sinh</th>
                        <th style="width: 10%">Phòng Ban</th>
                        <th style="width: 20%">Trình độ học vấn</th>
                        <th style="width: 7%">Địa chỉ</th>
                        <th style="width: 15%">Sở Thích</th>
                    </tr>
                    </thead>

                    <tbody id="low-salary-tbody">

                    </tbody>

                </table>

                <nav>
                    <ul class="pagination" id="low-salary-pagination">
                        <!-- Pagination buttons will be added here by JavaScript -->
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        // Handle the search button click event
        loadEmployees('/api/employee/high-salary', 'high-salary-tbody', 'high-salary-pagination', 1);
        loadEmployees('/api/employee/low-salary', 'low-salary-tbody', 'low-salary-pagination', 1);
        $('.btn-search').click(function (e) {
            e.preventDefault();
            $('#department').val('all');
            var keyword = $('#search-input').val();
            loadEmployees('/api/employee/search?name=' + keyword + "&salary=10000000", 'high-salary-tbody', 'high-salary-pagination', 1);
            loadEmployees('/api/employee/search?name=' + keyword + "&salary=10000000", 'low-salary-tbody', 'low-salary-pagination', 1);
        });

        $('#department').change(function () {
            var department = $(this).val();
            var url = '/api/employee/department';
            $('#search-input').val('');
            if (department !== 'all') {
                url += '?department=' + department + '&salary=10000000';
            }
            if(department === 'all'){
                url = '/api/employee/high-salary';
            }
            loadEmployees(url, 'high-salary-tbody', 'high-salary-pagination', 1);
            loadEmployees(url, 'low-salary-tbody', 'low-salary-pagination', 1);
            console.log(url);
        });

        function loadEmployees(url, tbodyId, paginationId, page) {
            $.ajax({
                url: url,
                type: 'GET',
                data: {
                    page: page
                },
                success: function (data) {
                    var tbody = $('#' + tbodyId);
                    tbody.empty();
                    data.employees.forEach(function (employee) {
                        var tr = $('<tr>');
                        tr.append('<td>' + employee.id + '</td>');
                        tr.append('<td>' + employee.name + '</td>');
                        tr.append('<td>' + employee.email + '</td>');

                        var fomatedSalary = new Intl.NumberFormat('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        }).format(employee.salary);

                        tr.append('<td>' + fomatedSalary + '</td>');

                        var dateOfBirth = new Date(employee.dateofbirth);
                        var formattedDate = dateOfBirth.getDate() + '/' + (dateOfBirth.getMonth() + 1) + '/' + dateOfBirth.getFullYear();


                        tr.append('<td>' + formattedDate + '</td>');
                        tr.append('<td>' + employee.department + '</td>');
                        tr.append('<td>' + employee.education + '</td>');
                        tr.append('<td>' + employee.address + '</td>');
                        tr.append('<td>' + employee.hobbies + '</td>');
                        tbody.append(tr);
                    });

                    var pagination = $('#' + paginationId);
                    pagination.empty();
                    for (var i = data.startPage; i <= data.endPage; i++) {
                        var li = $('<li class="page-item' + (i === page ? ' active' : '') + '">');
                        var a = $('<a class="page-link" href="#">' + i + '</a>');
                        a.data('page', i);  // Store the page number in a data attribute
                        li.append(a);
                        pagination.append(li);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("AJAX Error: " + textStatus);
                }
            });
        }

        $(document).on('click', '.page-link', function (e) {
            e.preventDefault();
            var page = $(this).data('page');  // Retrieve the page number from the data attribute
            var tabId = $(this).closest('.tab-pane').attr('id');
            var url = '/api/employee/search?name=' + $('#search-input').val() + '&salary=10000000';
            loadEmployees(url, tabId + '-tbody', tabId + '-pagination', page);
        });
    });


</script>
</body>
</html>