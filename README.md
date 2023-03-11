# :tangerine:Frutils: simple utility library for Java

[![Maven Central](https://img.shields.io/maven-central/v/in.frol/frutils.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22in.frol%22%20AND%20a:%22frutils%22)
[![Coverage Status](https://coveralls.io/repos/github/frolma/frutils/badge.svg?branch=main)](https://coveralls.io/github/frolma/frutils?branch=main)
![GitHub](https://img.shields.io/github/license/frolma/frutils)

The library, with its simple methods/utilities,
can help fill the gap in operations on various basic types
and improve code cleanliness, while being implemented
as pure functions and avoiding creating a dozen objects to check a simple condition.

###### &#9749; Requires JDK 17 or higher

### Frutils assortment is as follows:

- [Objects](#objects) - methods à la SQL Server's COALESCE or ORACLE's NVL and other ISNULL functions,
  in a convenient, concise and static "pack" for Java with lazy extensions.
- [Functions](#functions) - no-op lambdas, wrappers for non-checked exception and more
- [BigDecimals](#bigdecimals) - checks, comparisons and so on
- [Strings](#strings) - anything not found elsewhere for String or the method names seemed inappropriate
- [Collections](#collections) - checks ifnull, empty and split list into chunks
- [Booleans](#booleans)
- [UtilDates](#utildates) - util.Date and datetime API converters

**Just add the following snippet to dependencies:**

```
// Apache Maven
<dependency>
    <groupId>in.frol</groupId>
    <artifactId>frutils</artifactId>
    <version>1.0.1</version>
</dependency>

// Gradle Kotlin DSL
implementation("in.frol:frutils:1.0.1")
```

## Objects

###### There are only a few ways to check for null in business logic in Java:

- The old-fashioned way, verbose and explicit in the code with ifs.
- Or we can cover all variables with Optional objects, many do, but the purpose of Optional is "...primarily intended
  for use as a method return type", and the Oracle docs originally promoted it - "...the purpose of Optional is not to
  replace every single null reference in your codebase, but rather to help design better APIs...". And it's no fun when
  Optionals are created in a loop with millions of iterations or inside streams...
- And you can also use handy static methods, which is exactly what *Frutils* `Objects` suggest:

###### Objects.neNull - Not Equal to Null, selects the first arg with a !null value:

```
neNull(<returns the first non-null value in the row, ...>);

neNull(nullRef, nonNullRef);                 //result: nonNullRef

neNull(nonNullRef1, nonNullRef2);            //result: nonNullRef1

neNull(nullRef, "default");                  //result: "default"

neNull(nullRef, nonNullRef, "defaultValue"); //result: nonNullRef
```

###### If the value is not null, then apply the Function to it otherwise the value from the Supplier

```
neNull(<val>, <function if val not null>, <supplier if val is null>);

neNull(valueNonNull, service::processValue, service::getBackupData); //result: processed value

neNull(valueNull, service::processValue, service::getBackupData); //result: default value
```

###### Other options with lazy implementations, with Function for non-null argument:

```
neNull(<val>, <function if val not null>);

neNull(refNull, Foo::getBar);               //result: null

neNull(refNonNull, Foo::getBar);            //result: value of field 'bar' from 'refNonNull'

neNull(value, BigDecimal::new, BigDecimal::negate, BigDecimal::toEngineeringString);
neNull(previousResult, "N/A");               //result: transformed value

neNull(null, BigDecimal::new, BigDecimal::negate, BigDecimal::toEngineeringString);
neNull(previousResult, "N/A");               //result: "N/A"
```

###### and just Supplier for null argument (including varargs):

```
neNull(<val>, <supplier if val is null>);

neNull(nullRef, supplier);                                           //result: result from supplier

neNull(nullRef, () -> obtainDefaultValue());                         //result: defaultValue

neNull(nullRef, () -> getNullRef(), () -> getDefaultRef2(), ...);    //result: defaultRef2

neNull(nullRef, consumerGetNull, consumerGetDefault);                //result: default

neNull(nonNullRef, () -> getNullRef(), () -> getDefaultRef2(), ...); //result: nonNullRef
```

###### To avoid creating a Stream, Filters, Buckets, etc. are also shown:

```
allNull(..), allNotNull(..), anyNotNull(..), anyNull(..) ... etc.

castOr(<val>, <targetType>, <defaultValue>)
castOrGet(<val>, <targetType>, <supplier with default value>)

ListBlobItem item = retrieveBlob();
CloudBlockBlob blockBlob = castOr(item, CloudBlockBlob.class, getDefaultBlockBlob());
CloudPageBlob pageBlob = castOrGet(item, CloudPageBlob.class, some::getDefaultPageBlob);
```

## Functions

###### third-party methods with consumer, function and your plugs, after formatting is worse:

```
object.method(variable, collection, (val) -> {}, (val) -> val);

object.method(variable, collection, (val) -> {
}, (val) -> val);
```

###### with `Functions` is better:

```
object.method(variable, collection, doNothing(), justReturn());

object.method(variable, collection, emptyConsumer(), emptyUnaryOperator());
```

###### wrapper for `unchecked` exceptions in functions and other interfaces would also be useful:

```
... .map(service::uncheckedFunction) // сompilation error

... object.method(service::uncheckedConsumer) // сompilation error

... .map(unchecked(service::uncheckedFunction)) // OK

... object.method(unchecked(service::uncheckedConsumer)) // OK
```

## BigDecimals

###### often operations with `BigDecimal` are left in the code like this:

```
BigDecimal variable = ...

variable.signum() < 0

variable.signum() >= 0

variable == null || variable.compareTo(BigDecimal.ZERO) == 0

variable != null && variable.compareTo(BigDecimal.ZERO) != 0

variable1.compareTo(variable2) >= 0
```

###### `BigDecimals` methods tidy this up:

```
isNegative(variable)

notNegative(variable)

isNullOrZero(variable)

neitherNullNorZero(variable)

greaterOrEquals(variable1, variable2)

// as well as
eq(..), ne(..), gt(..), lt(..), gte(..), lte(..), lte(..), abs(.) 
```

## Strings

###### length, isBlank, notBlank isNumeric, isAlphaNumeric, isSignedNumeric, etc.

```
length(null)   == 0
length("")     == 0
length("  ")   == 2
length("123")  == 3

isAlphaNumeric(<all symbols are letter or digit>) == true
notAlphaNumeric(<all symbols are letter or digit>) == false

isNumeric(<any number (for floating point numbers the decimal separator is ".")>) == true
isNumeric("123")         == true
notNumeric(" 123")       == false

isSignedNumeric("-123")  == true
notSignedNumeric("-123") == false

isBlank(null)     == true 
isBlank("")       == true 
notBlank("  ")    == false 
isBlank("_")      == false 
```

## Collections

```
hasItems(<collection or map>)
hasItems(<null or empty>) == false
hasItems(<collection or map with any item(s)>) == true

hasNoItems(<collection or map>)
hasNoItems(<null or empty>) == true
hasNoItems(<collection or map with any item(s)>) == false

firstItem(<list or set>)
firstItem(List.of(1, 2, 3)) == 1
firstItem(Set.of("a", "b", "c")) == "a"

lastItem(<list or set>)
lastItem(List.of(1, 2, 3)) == 3
lastItem(Set.of("a", "b", "c")) == "c"

toChunks(list, chunkSize)
toChunks(List.of(1,2,3,4,5), 3) == List.of(List.of(1,2,3), List.of(4,5)) 

etc...
```

## Booleans

```
// if it is necessary to check the nullable Bool variable:
if (!Boolean.TRUE.equals(object.getBoolean())) {

// try this instead:
if (neTrue(object.getBoolean()) {
```

```
eqTrue(null)      // false
eqTrue(falseVar)  // false
eqTrue(trueVar)   // true

neTrue(null)      // true
neTrue(falseVar)  // true
neTrue(trueVar)   // false

getIfTrue(<condition>, <value if condition eq true>)
getIfNeTrue(<condition>, <value if condition ne true>)

supplyIfTrue(<condition>, <supplier if condition eq true>)
supplyIfNeTrue(<condition>, <supplier if condition ne true>)

runIfTrue(<condition>, <runnable if condition eq true>)
runIfNeTrue(<condition>, <runnable if condition ne true>)
```

## UtilDates

###### Converters for java.util.Date if unfortunately you have to use it

## To be continued...

---

###### C'est a table d'hôte, pas à la carte.