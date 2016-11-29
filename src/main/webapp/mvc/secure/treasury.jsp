<%--
  Created by IntelliJ IDEA.
  User: nathanielellsworth
  Date: 11/24/16
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    response.setHeader("Cache-Control", "no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Latest compiled and minified CSS -->
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="/mvc/secure/account/savings">Home</a></li>
                <li role="presentation"><a href="/mvc/secure/account/all">Treasury Bills</a></li>
                <c:if test="${user_loggedin_perms.containsKey('ADMIN_ADD_USER')}">
                    <li role="presentation"><a href="/mvc/secure/admin/users">Users</a></li>
                </c:if>
                <li role="presentation"><a href="/mvc/open/logout">Logout</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">Other Accounts</h3>
    </div>
    <div class="row marketing">
        <div class="col-lg-10">
            <h4>View Current and Recently Issued Treasury Bills</h4>

            <p/>
            <p/>
            <p/>
            <div class="pull-left">
                <c:if test="${account_pager.previous}">
                    <a class="btn btn-default btn-sm"
                       href="/mvc/secure/account/all?page=<c:out value="${account_pager.previousPage}"/>">Previous</a>
                </c:if>
            </div>
            <div class="pull-right">
                <c:if test="${account_pager.next}">
                    <a class="btn btn-default btn-sm"
                       href="/mvc/secure/account/all?page=<c:out value="${account_pager.nextPage}"/>">Next</a>
                </c:if>
            </div>

            <table class="table">


                <th>Issue Date</th>
                <th>Security Term</th>
                <th>(0.01% Annual Bank return)<br/>
                    Current Return %
                </th>
                <th><a href='https://ycharts.com/indicators/1_month_treasury_rate' type="submit"
                       class="btn btn-info" role="button" target="_new">View Historical Data</a></th>
                <c:if test="${user_loggedin_perms.containsKey('USER_BUY_TBILLS')}">
                    <th><a href='http://www.treasurydirect.gov/indiv/TDTour/open_account.htm' type="submit"
                           class="btn btn-info" role="button" target="_new">Open a Treasury Account</a></th>
                </c:if>



                    <tr>
                        <th>
                            <div/>
                            <form action="/mvc/secure/account/all/tbills" method="get">
                                <c:forEach items="${aTBill}" var="date">
                    <tr>
                        <td><c:out value="${date.issueDate}"/></td>
                        <td><c:out value="${date.securityTerm}"/></td>
                        <td><c:out value="${date.highInvestmentRate}"/></td>
                    </tr>
                    </c:forEach>
                            </form>

            </table>
        </div>


    </div> <!-- /container -->

</div>
</body>
</html>

