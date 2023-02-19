# :tangerine:Frutils: simple utility library for Java

![Maven Central](https://img.shields.io/maven-central/v/in.frol/frutils)
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
- [Collections](#collections) - checks ifnull, empty and split list into chunks
- [UtilDates](#utildates) - util.Date and datetime API converters

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
neNull(nullRef, nonNullRef);                 //result: nonNullRef

neNull(nonNullRef1, nonNullRef2);            //result: nonNullRef1

neNull(nullRef, "default");                  //result: "default"

neNull(nullRef, nonNullRef, "defaultValue"); //result: nonNullRef
```

###### Lazy implementations, with Function for non-null argument:

```
neNull(nullRef, SomeNonNullClass::getField); //result: null

neNull(nonNullRef, Class::getFoo);           //result: value of field 'foo' from 'nonNullRef'

neNull(value, BigDecimal::new, BigDecimal::negate, BigDecimal::toEngineeringString);
neNull(previousResult, "N/A");               //result: transformed value

neNull(null, BigDecimal::new, BigDecimal::negate, BigDecimal::toEngineeringString);
neNull(previousResult, "N/A");               //result: "N/A"
```        

###### and Supplier for null argument (including varargs):

```
neNull(nullRef, consumer);                                           //result: result from consumer

neNull(nullRef, () -> obtainDefaultValue());                         //result: defaultValue

neNull(nullRef, () -> getNullRef(), () -> getDefaultRef2(), ...);    //result: defaultRef2

neNull(nullRef, consumerGetNull, consumerGetDefault);                //result: default

neNull(nonNullRef, () -> getNullRef(), () -> getDefaultRef2(), ...); //result: nonNullRef
```

###### To avoid creating a Stream, Filters, Buckets, etc. are also shown:

```
allNull(..), allNotNull(..), anyNotNull(..), anyNull(..) ... etc.
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
```

## Collections

```
isEmpty(list)
notEmpty(list)

isEmpty(map)
notEmpty(map)

toChunks(list, chunkSize)
etc...
```

## UtilDates

###### Converters for java.util.Date if unfortunately you have to use it

## To be continued...

---

###### C'est a table d'hôte, pas à la carte.