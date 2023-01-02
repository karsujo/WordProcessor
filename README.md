# cs542-fall-22-assign4

### Authors:
### Karthik Shanmugam - kshanmu1@binghamton.edu
### Yash Sanjay Makwana - ymakwan1@binghamton.edu
-----------------------------------------------------------------------
-----------------------------------------------------------------------
## Slack Days Used : <span style="color:red">*2*</span>

-----------------------------------------------------------------------

## ANT Commands 
 **Note: build.xml is present in wordProcessor/src folder.**

 **IMPORTANT! : Run all the following commands from the ~/cs542-fall-22-assign4-team-kshanmu1-ymakwan1/ directory**


#### Instruction to clean:

**Command: ant -buildfile wordProcessor/src/build.xml clean**
Description: It cleans up all the .class files that were generated when you
compiled your code.

#### Instruction to compile:

 **Command: ant -buildfile wordProcessor/src/build.xml all**

Description: Compiles your code and generates .class files inside the BUILD folder.

#### Instruction to run:

 **Command: ant -buildfile wordProcessor/src/build.xml run -Darg0=input_sentences.txt -Darg1=british_american_words.txt -Darg2=3 -Darg3=topKResults.txt -Darg4=spellCheckResults.txt -Darg5=1 run**

Format: InputFile, MappingFile, K, TopKOutputFile, SpellCheckOutputFile, DEBUG_LEVEL

Note : all the input/output files are expected to be at the level of the src/ directory. Example:
wordProcessor/src
wordProcessor/input_sentences.txt

-----------------------------------------------------------------------
## Description

### Code Design :

**Overall Architecture**

**MyArray**

The MyArray is of the type MyElement, and implments the array data structure. It exposes two methods to interact with the data : getIterator -> to iterate over the array, and an accept() method to accept visitors to utilize data in the array. 

**Visitor**

There are two visitors in this application : The TopKFrequent visitor and the SpellCheck visitor. 

The **TopKFrequent** visitor simply goes through every sentence in the input file and computes the top 'K' words found in the text and writes the same to output. 

The **SpellCheck** visitor implements two strategies:

*Case Sensitive Spell Check*:
Goes through every word in the input file and matches words exactly as mentioned in the british-american mapping file, and replces them. 

*Case In-Sensitive Spell Check*:
Goes through every word in the input file and matches words irrespective of their casing in the british-american mapping file, and replces them. 

### **Core Workings :**


**The Visitor Pattern**

```
    @Override
    public void accept(VisitorI visitor) {
        visitor.visit(this);
    }

```
The MyArray class has an accept method which takes in specialized visitors in order to use the data stored in MyArray. The accept method uses double dispatch to select which visitor to call based on the runtime types of the calling class and the called visitor. 

```
    @Override
    public void visit(MyElementI myElement) {
        CaseSensitiveStrategy caseSensitiveStrategy = new CaseSensitiveStrategy(outputFileProcessor, inputFileProcessor);
        
        CaseInsensitiveStrategy caseInSensitiveStrategy = new CaseInsensitiveStrategy(outputFileProcessor, inputFileProcessor);

        //Execute Strategy 1
        executeStrategies(myElement, caseInSensitiveStrategy);
        //Execute Strategy 2
        executeStrategies(myElement, caseSensitiveStrategy);
        
        outputFileProcessor.closeInterface();
    }
```

Within the visitor, the myArray instance is passed, which the visitor uses by calling the getIterator() method. 

**Strategy Example**

Input text  : He was reading a novel by his favorite british author.

Map-text : British:American

**Strategy 1 : Case InSensitive Matching**

He was reading a novel by his favorite American author.

**Strategy 2 : Case Sensitive Matching**

He was reading a novel by his favorite british author.

### Algorithm :

No particular algorithm is used.
 
### Data-Structure :

The MyArray class internally uses an **ArrayList** to store sentences. The TopKFrequentVisior uses a PriorityQueue with Comparators to perform the algorithm. 

### Program I/O

-> The program accepts 6 arguments
    - input_sentences.txt file (This input file contains sentences in form of a paragraph).
    - british_american_words.txt file (This file contains british to american words in the form of british:american).
    - topK (This is to calculae the Top K Frequent words in the file of sentences).
    - topKResults.txt (This file stores the Top K Frequent words).
    - spellCheckResults.txt (This file stores the spell check result implementation of SpellCheck Visitor for both  the stratergies).
    - debugLevel (This is to set the debug level for the Logger)
-> The Visitor pattern is used to visit
    - TopK - Compute TopK words of the whole input file.
    - SpellCheck - Checks the spellings of the words from the british_american_words file and replaces the equivalent british words to american words via 2 stratergies(case-sensitive & case insensitive).


### Exception Handling :

All exceptions are handled either by the ExceptionHandler class or the handleException() method implemented in the Results class. 
For any exception,
 - A message is constructed either by using Java's default exception message or a custom message by the user is used.
 - This message is written into the errorLog file
 - This message is written to standard out (console)
 - The stackTrace of the exception is printed onto the console if program is running in DEBUG mode.
 - Program control is relinquished by exiting (System.exit(1))


**Even if the provided output file with the given name does not exist, the program will CREATE a new file and write into it**

**If the invalid characters in inpt file**:
EXCEPTION : Invalid Characters detected in the file. Program Exiting ... 

**If the input file is blank or empty**:
EXCEPTION : Program outputs nothing. 

**If the user input for K is <= 0  OR  greater than the total number of words in the input file**:
EXCEPTION : Invalid value of K. K must be > 0 and lesser than total no of words in input file.


**If input file not found**:
EXCEPTION : Unable to locate file : {fileName}

### Program Outputs :

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense."

Date: 7th December 2022
