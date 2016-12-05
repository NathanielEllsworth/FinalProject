<%--
  Created by IntelliJ IDEA.
  User: nathanielellsworth
  Date: 11/14/16
  Time: 5:23 PM
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


<!-- Top of the Page Navigation Links -->
<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <!-- Login Automatically sends the Authenticated User to their 'Home Page' -->
                <li role="presentation" class="active"><a href="/mvc/secure/account/savings">Home</a></li>


                <!-- The Treasury Bills Navigation link sends the User to the Live Data Stream
                 of current US Treasury Bills directly from the United States Treasury Department -->
                <li role="presentation"><a href="/mvc/secure/account/all">Treasury Bills</a></li>


                <!-- Depending on the privileges given to a User, the key below will grant or deny a user access
                  of enrolling or deleting other individuals into the group plan -->
                <c:if test="${user_loggedin_perms.containsKey('ADMIN_ADD_USER')}">
                    <li role="presentation"><a href="/mvc/secure/admin/users">Users</a></li>
                </c:if>


                <!-- This Link Automatically Signs the User out and forwards them back to the Login page -->
                <li role="presentation"><a href="/mvc/open/logout">Logout</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">My Investment and Savings Account</h3>
    </div>
</div>


<div class="row marketing">
    <center>
        <div class="col-lg-11">
            <p/>
            <h4>Transaction History</h4>

            <p/>
            <div class="pull-left">



                <!-- When More than 8 transactions have occurred, the user will be able to page through
                  the transactions 8 at a time-->
                <c:if test="${account_pager.previous}">
                    <!--turns link into button-->  <a class="btn btn-default btn-sm"
                       href="/mvc/secure/account/savings?page=<c:out value="${account_pager.previousPage}"/>">Previous</a>
                </c:if>
            </div>
            <div class="pull-right">
                <c:if test="${account_pager.next}">
                    <a class="btn btn-default btn-sm"
                       href="/mvc/secure/account/savings?page=<c:out value="${account_pager.nextPage}"/>">Next</a>
                </c:if>
            </div>



            <table class="table">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Description</th>
                    <th>Credit (+)</th>
                    <th>Debit (-)</th>
                    <th>Term</th>
                    <th>T-Bill Return</th>
                    <th>Bank Return (0.01%)</th>
                    <th>Risk-Free<br/>
                        Return Increase</th>
                    <!--<th>Posted Balance</th>-->
                    <th>Available Balance</th>
                </tr>
                </thead>
                <style>
                    .greentext {
                        color: green;
                        font-weight: bold;
                    }
                </style>
                <tbody>

                <c:forEach items="${all_transactions}" var="aAccount">
                    <tr>
                        <td><c:out value="${aAccount.date}"/></td>
                        <td><c:out value="${aAccount.type}"/></td>
                        <td><c:out value="${aAccount.description}"/></td>
                        <td><c:out value="${aAccount.credit}"/></td>
                        <td><c:out value="${aAccount.debit}"/></td>
                        <td><c:out value="${aAccount.term}"/></td>
                        <td><c:out value="${aAccount.tBillRate}"/></td>
                        <td><c:out value="${aAccount.bankRate}"/></td>
                        <td class="greentext"><c:out value="${aAccount.rateDifference}"/></td>
                        <!--<td><c:out value="${aAccount.postedBalance}"/></td>-->
                        <td><c:out value="${aAccount.availableBalance}"/></td>



                        <!-- If given permission, the logged-in User can make changes to their transaction history
                        when the key below is enabled -->
                        <c:if test="${user_loggedin_perms.containsKey('USER_EDIT_TRANSACTIONS')}">
                        <td><a href="/mvc/secure/user/account/savings/delete?id=<c:out value="${aAccount.id}"/>">X </a>
                            </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </center>

</div>

<footer class="footer">
    <div class="pull-center">
        Current Page: <c:out value="${account_pager.currentPage}"/>
        Total Pages: <c:out value="${account_pager.totalPages}"/>
        Total Transactions: <c:out value="${account_pager.totalTransactions}"/>
    </div>



    <!-- If given permission, the logged-in User can make changes to their transaction history when the key below is
     enabled -->
    <c:if test="${user_loggedin_perms.containsKey('USER_EDIT_TRANSACTIONS')}">
        <center>
            <div>
                Legend: Remove Transaction "X"


            </div>
        </center>


        <h4>Add Transactions</h4>
        <c:if test="${error_message != null}">
            <div class="alert alert-danger"><c:out value="${error_message}"/></div>
        </c:if>

        <form method="post" action="/mvc/secure/user/account/savings/add">
            <table class="table">
                <input type="hidden" name="id" value="<c:out value="${id}"/>"/>


                <tr>
                    <td><input type="date" name="date" placeholder="Date "></td>
                    <td><input type="text" name="type" placeholder="Type: Transfer"></td>
                    <td><input type="text" name="description" placeholder="Description "></td>
                    <td><input type="text" name="debit" placeholder="Debit (-) "></td>
                    <td><input type="text" name="credit" placeholder="Credit (+) "></td>
                </tr>
                <tr>
                    <td><input type="text" name="term" placeholder="Term:  3 Months "></td>
                    <td><input type="text" name="tBillReturn" placeholder="Return "></td>
                    <td><input type="text" name="bankReturn" placeholder="Bank Return: 0.01 "></td>
                    <td><input type="text" name="returnIncrease" placeholder="Return Increase "></td>
                    <!--<td><input type="text" name="postedBalance" placeholder="Posted Balance "></td>-->
                    <td><input type="text" name="availableBalance" placeholder="Available Balance "></td>
                </tr>
            </table>
            <div>
                <center><input type="submit" name="Save"/></center>
            </div>
        </form>
    </c:if>

    <p>&copy; 2016 Click Here For Money, Inc.</p>
</footer>

</div> <!-- /container -->


</body>
</html>
