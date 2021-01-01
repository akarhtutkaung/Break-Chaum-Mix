READ ME
-------
To run the mix with target-rounds, run "run.sh"

The "mix.java" will only use "target-rounds" to compute the result.csv
In order to compute using other files, will need to change some coding inside the mix.java

Note# mixExample.java will use "example-rounds" to compute the result.csv

Comparing correctness
---------------------
For testing on percentage of correctness, enter the following in the bash

javac testing.java
java testing [file1 path] [file2 path]