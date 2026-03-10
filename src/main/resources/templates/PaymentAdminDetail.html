<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Payment Admin Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body { background: #f4f7f6; font-family: 'Segoe UI', sans-serif; }
        .navbar { background: linear-gradient(135deg, #4e54c8, #8f94fb); }
        .card { border: none; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.1); max-width: 600px; margin: 40px auto; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" th:href="@{/}">ADV Shop</a>
    </div>
</nav>

<div class="container">
    <div class="card p-5">
        <h3 class="fw-bold mb-4 text-center">Payment Admin Detail</h3>

        <table class="table table-bordered mb-4">
            <tr>
<th>Payment ID</th>
                <td th:text="${payment.id}"></td>
            </tr>
            <tr>
                <th>Method</th>
                <td th:text="${payment.method}"></td>
            </tr>
            <tr>
                <th>Status</th>
                <td>
                    <span class="badge"
th:classappend="${payment.status == 'SUCCESS'} ? 'bg-success' :
        (${payment.status == 'REJECTED'} ? 'bg-danger' : 'bg-warning text-dark')"
th:text="${payment.status}">
                    </span>
                </td>
            </tr>
            <tr th:each="entry : ${payment.paymentData}">
                <th th:text="${entry.key}"></th>
                <td th:text="${entry.value}"></td>
            </tr>
        </table>

        <form th:action="@{/payment/admin/set-status/{id}(id=${payment.id})}" method="post">
            <div class="mb-3">
                <label class="form-label fw-bold">Set Status</label>
                <select name="status" class="form-select" required>
                    <option value="SUCCESS">SUCCESS</option>
                    <option value="REJECTED">REJECTED</option>
                    <option value="WAITING_PAYMENT">WAITING_PAYMENT</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100">Update Status</button>
        </form>

        <div class="text-center mt-3">
            <a th:href="@{/payment/admin/list}" class="text-muted text-decoration-none small">← Back to All Payments</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>