# **Selenium Tests for webshop**


Selenium TestCases for [HGShop](https://www.hgshop.hr/).

---

This project covered 2 test suites, with 12 test cases altogether

---
###Login Test

This test suite includes 4 test cases:
-Login with correct credentials
-Login with correct username, false password
-Login with false username
-Sql injection attack

All 4 test cases passed.

---
###Chart Quantity Test

This test suide includes 8 test cases: 
- Chart quantity test when user adds one product in chart and then continue shopping. **_PASSED_**
- Chart quantity test when user adds 2 of same product in chart and then continue shopping. **_FAILED_**,*Chart shows that 3 product are added in chart instead of 2.*
- Chart quantity test when user adds one product in chart and then ends shopping. **_PASSED_**
- Chart quantity test when user adds 2 of same product in chart and then ends shopping. **_PASSED_**
- Chart quantity test when user manually write in quantity of product he wants, instead of clicking + to increase, then continue shopping. **_FAILED_**,*Chart not only shows wrong, but also ignoring inserted quantity, only one product is added*
- Chart quantity test when user manually write in quantity of product he wants, instead of clicking + to increase, then end shopping. **_FAILED_**,*Chart not only shows wrong, but also ignoring inserted quantity, only one product is added*
- Chart quantity test when user adds one product, then he adds that same product again.**_FAILED_**,*System ignored second adding, we would expect that quantity of that product would increase in this case, but it remains same*
- Chart quantity test when user adds 50 000 of playstations in chart.**_FAILED_**,*User could finish order, we would expect system to stop him for ordering that much of one product, because probably it is impossible to get 50 000 playstations.*