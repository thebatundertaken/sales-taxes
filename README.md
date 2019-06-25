# SALES TAXES #

## Aproach ##

* [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design) (DDD) instead of Test Driven Design (TDD). _Domain-Driven Design: Tackling Complexity in the Heart of Software, Addison-Wesley, 2003, ISBN 0321125215_
* [Hexagonal Architecture](https://alistair.cockburn.us) (aka Ports & Adapters) rather than Clean Architecture. _Dr. Alistair Cockburn, 2005._ 
* [Solid principles](https://en.wikipedia.org/wiki/SOLID). _Agile Software Development, Principles, Patterns, and Practices, Uncle Bob, 2002, ISBN 0135974445_
* [Software Design Patterns](https://en.wikipedia.org/wiki/Software_design_pattern). _Design Patterns: Elements of Reusable Object-Oriented Software, Addison-Wesley, 1995, ISBN 0201633612_

* Checkstyle [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
* Java 11
* Junit5 + Mockito (AAA test pattern)
* Although no 3rd party lib should be use, I took the liberty of adding Lombok to reduce boiler plate code.

![DDD](https://i.imgur.com/N7DEvm4.jpg)

## The problem ##

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax applicable on all imported
goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price
(including tax), finishing with the total cost of the items, and the total amounts of sales taxes
paid.

The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...

### INPUT ###

Input 1:
```
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
```
Input 2:
```1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50
```
Input 3:
```1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
```
### OUTPUT ###

Output 1:
```1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83
```
Output 2:
```
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15
```
Output 3:
```
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
```