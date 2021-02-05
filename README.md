# simple-cmd
Simple CMD CLI for training purposes

## TODO:
* prepare jenkins
* extend Readme:
    * how to: import project/ build / deploy / run / test
    * how to extends commands
* introduce bugs
* introduce extension points for features: 
    * check if not too complex
    * prepare a guide and document the worked out solution
* align features/bugs with training sessions
* cleanup pom.xml: remove unnecessary dependencies
* fix build: currently not possible run outside of Intellij (cmd)
* adjust Devon IDE Settings
* Eclipse project incl run config
* Tests:
    * Write some working Unit-Tests
    * provide information on how to prepare "test data"

# Known bugs
|Issue-#|Description|
|---|---|
| 1   |"dir" command: sorting direction is incorrect (asc vs desc) |
| 2   |"dir" command: path should be printed only in specific conditions (file vs directory) |
# New features
|Feature-#|Description|     
|---|---|     
| 1 |"dir" command: print files with relative path (--short) |
| 2 |"dir" command: pass desired path/directory as argument (--path) |
| 3 |"dir" command:  introduce feature, check if passed file path is a file (--check) |