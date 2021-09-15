****The Adapter and Facade Pattern:****
--

For example, Lets say we have adapter of monitor and outlet. We usaually plug adapter to outlet and monitor will expect right amount of power regardless type of 
outlet. We can say that; Adapter changes the interface of the outlet into one that monitor expects.

Say, we have existing service which uses some vendor libaray interfaces, and we wrote new code to create some features by use old vendor interfaces.
But problem is, new vendor interface have changed thier structure, which will create problem for new code.
We don't want to change our big code changes and use new vendor libarary interfaces. Hence, we developers as client of vendor libaray expect some behaviour(methods) which we want to have without changes in our new code. 
Here, adapter class come into the picture which implements method of vendor libarary in expected manner.

Vendor libarary:- `outlet`

Our new code :- `monitor`

Adapter class:- `adapter`

----

