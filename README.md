# hexlife

Basic compile "scalac *.scala"
Run with
scala HexLife

Supports the assignment parameters except the -i probabilty
Supported params are
- 12: use the 12 neighbor rules (default is the 6 neighbor rules)
-size n: specify the size of the grid to be n X n (default is 100)
-f filename: read in the initial configuration from the specified file (see below for input file format)
-g n: specifiy the number of generations to simulate (default is 10)
-p n: specify that every nth generation should be printed (for plain text output only, default is 1)

Also supports -G to turn on the GUI display.