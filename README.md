# :tangerine:Frutils: simple utility library for Java

![Maven Central](https://img.shields.io/maven-central/v/in.frol/frutils)
![GitHub](https://img.shields.io/github/license/frolma/frutils)

&#9749; Requires JDK 17 or higher

The library, with its simple methods/utilities,
can help fill the gap in operations on various basic types
and improve code cleanliness, while being implemented
as pure functions and avoiding creating a dozen objects to check a simple condition.

### Frutils assortment is as follows:

- [Functions](#functions) - no-op lambdas, wrappers for non-checked exception and more
- [BigDecimals](#bigdecimals) - checks, comparisons and so on

## Functions

third-party methods with consumer, function and your plugs,
after formatting is worse:

```
object.method(variable, collection, (val) -> {}, (val) -> val);

object.method(variable, collection, (val) -> {
}, (val) -> val);
```

with `Functions` is better

```
object.method(variable, collection, doNothing(), justReturn());

object.method(variable, collection, emptyConsumer(), emptyUnaryOperator());
```

wrapper for `unchecked` exceptions in functions and other interfaces would also be useful:

```
... .map(service::uncheckedFunction) // сompilation error

... object.method(service::uncheckedConsumer) // сompilation error

... .map(unchecked(service::uncheckedFunction)) // OK

... object.method(unchecked(service::uncheckedConsumer)) // OK
```

## BigDecimals

often operations with `BigDecimal` are left in the code like this:

```
BigDecimal variable = ...
variable.signum() < 0
variable.signum() >= 0
variable == null || variable.compareTo(BigDecimal.ZERO) == 0
variable != null && variable.compareTo(BigDecimal.ZERO) != 0
variable1.compareTo(variable2) >= 0
variable1 == null || variable2 == null || variable3 == null || var...
or Stream.of(variable1, variable2, variable3, variable4).anyMatch(...

```

`BigDecimals` methods tidy this up:

```
isNegative(variable)
notNegative(variable)
isNullOrZero(variable)
neitherNullNorZero(variable)
greaterOrEquals(variable1, variable2)
anyNull(variable1, variable2, variable3, variable4)
```

## To be continued...
