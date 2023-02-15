# :tangerine:Frutils: simple utility library for Java

![Maven Central](https://img.shields.io/maven-central/v/in.frol/frutils)
![GitHub](https://img.shields.io/github/license/frolma/frutils)

:coffee: *Requires JDK 17 or higher*

:newspaper: The library, with its simple methods/utilities,
can help fill the gap in operations on various basic types
and improve code cleanliness, while being implemented
as pure functions and avoiding creating a dozen objects to check a simple condition. 

### Frutils assortment is as follows:
- [Functions](#functions)
- [BigDecimals](#bigdecimals)



## Functions 
To be continued...


## BigDecimals

before:
```
BigDecimal variable = ...
variable.signum() < 0
variable.signum() >= 0
variable == null || variable.compareTo(BigDecimal.ZERO) == 0
variable != null && variable.compareTo(BigDecimal.ZERO) != 0
variable1.compareTo(variable2) >= 0
variable1 == null || variable2 == null || variable3 == null || variable4 == null
or Stream.of(variable1, variable2, variable3, variable4).anyMatch(...

```
after:
```
isNegative(variable)
notNegative(variable)
isNullOrZero(variable)
neitherNullNorZero(variable)
greaterOrEquals(variable1, variable2)
anyNull(variable1, variable2, variable3, variable4)
```


## To be continued...
