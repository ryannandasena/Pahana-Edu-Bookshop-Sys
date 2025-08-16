<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect(request.getContextPath() + "/login.jsp"); return; }
%>
<html>
<head>
    <title>Add Bill</title>
    <script>
        function onCustomerChange(sel) {
            const units = sel.options[sel.selectedIndex].getAttribute('data-units');
            document.getElementById('unitsCap').textContent = units;
            updateUsedUnits();
        }
        function addRow() {
            const tpl = document.getElementById('itemRowTemplate').innerHTML;
            const box = document.createElement('div');
            box.className = 'row';
            box.innerHTML = tpl;
            document.getElementById('itemsContainer').appendChild(box);
        }
        function removeRow(btn) {
            btn.closest('.row').remove();
            updateUsedUnits();
        }
        function updateUsedUnits() {
            const qtyInputs = document.querySelectorAll('input[name="quantity[]"]');
            let total = 0;
            qtyInputs.forEach(i => total += (parseInt(i.value) || 0));
            document.getElementById('unitsUsed').textContent = total;
            const cap = parseInt(document.getElementById('unitsCap').textContent) || 0;
            const warn = document.getElementById('limitWarn');
            if (total > cap) { warn.style.display = 'inline'; } else { warn.style.display = 'none'; }
        }
        document.addEventListener('input', (e) => {
            if (e.target && e.target.name === 'quantity[]') updateUsedUnits();
        });
    </script>
</head>
<body>
<h2>Add New Bill</h2>
<p>Logged in as: <%= user.getUsername() %> |
   <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a>|
   <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a></p>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="<%= request.getContextPath() %>/bill" method="post">
    <label>Customer:</label>
    <select name="customer_account_number" onchange="onCustomerChange(this)" required>
        <option value="">-- Select --</option>
        <c:forEach var="c" items="${customers}">
            <option value="${c.accountNumber}" data-units="${c.unitsConsumed}">
                ${c.name} (Acc: ${c.accountNumber})
            </option>
        </c:forEach>
    </select>

    <p>Units allowed: <b id="unitsCap">0</b> |
       Units added: <b id="unitsUsed">0</b>
       <span id="limitWarn" style="color:red; display:none;">(Limit exceeded!)</span>
    </p>

    <!-- items -->
    <div id="itemsContainer">
        <div class="row">
            <select name="item_id[]" required>
                <option value="">Select item</option>
                <c:forEach var="it" items="${items}">
                    <option value="${it.itemId}">${it.itemName} - ${it.price}</option>
                </c:forEach>
            </select>
            <input type="number" name="quantity[]" min="1" value="1" required />
            <button type="button" onclick="removeRow(this)">Remove</button>
        </div>
    </div>

    <button type="button" onclick="addRow()">+ Add another item</button>
    <br/><br/>
    <button type="submit">Generate Bill</button>
</form>

<!-- Hidden template -->
<script type="text/template" id="itemRowTemplate">
    <select name="item_id[]" required>
        <option value="">Select item</option>
        <c:forEach var="it" items="${items}">
            <option value="${it.itemId}">${it.itemName} - ${it.price}</option>
        </c:forEach>
    </select>
    <input type="number" name="quantity[]" min="1" value="1" required />
    <button type="button" onclick="removeRow(this)">Remove</button>
</script>
</body>
</html>
