# User Guide

## Table of content
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features and Commands](#3-features-and-commands)

    3.1 [Add Todo: `todo`](#31-add-todo-todo)
    
    3.2 [Add Event: `event`](#32-add-event-event)

    3.3 [Add Deadline: `deadline`](#33-add-deadline-deadline)

    3.4 [List All: `list`](#34-list-all-list)

    3.5 [Mark as Done: `done`](#35-mark-as-done-done)

    3.6 [Delete task: `delete`](#36-delete-task-delete)

    3.7 [Find by key phrases: `find`](#37-find-by-key-phrases-find)

    3.8 [Filter by date: `doneby`](#38-filter-by-date-doneby)

    3.9 [Exit the program: `bye`](#39-exit-the-program-bye)

4. [Command summary](#4-command-summary)

5. [Notes](#5-notes)

## 1. Introduction
Duke is an todo list management application. It is designed
for users who are familar with CLI to manage their tasks 
fast and painless.  

## 2. Quick Start 
1. Download and install Java 8 or above on your machine.
2. Download the latest release from [here](https://github.com/wgzesg/ip).
3. Copy the .jar file into a the folder that you wish to use as the home directory of 
this application
4. Open command line app and go the the home directory of the application and run
    ```
    java -jar Duke.jar
    ```
   

## 3. Features and Commands
### 3.1 Add Todo: `todo`
Add a `todo` task to the task list

Format: ```todo <Task Description>```

Example 
```todo read books```

Expected outcome:
```
Got it. I've added this task:
   [T][✘] read books
```

### 3.2 Add Event: `event`
Add a `event` task to the task list

Format: ```event <Task Description> /at <Date-Time>```

This supports date time input of multiple format. [1]

If the input is not in the listed formats, the 
exact user input will be stored but it will not
be recognised as a valid time.


Example 1
```event CS2113 lecture /at 2020/09/21 1600```

Expected outcome:
```
Got it. I've added this task:
[E][✘] CS2113 lecture (at: 2020/09/21 1600)
```

Example 2
```event CS2113 exam /at tomorrow```

Expected outcome:
```
Got it. I've added this task:
[E][✘] CS2113 exam (at: tomorrow)
```

### 3.3 Add Deadline: `deadline`
Add a `deadline` task to the task list

Format: ```deadline <Task Description> /by <Date-Time>``` 

This supports date time input of multiple format.[1]

If the input is not in the listed formats, the 
exact user input will be stored but it will not
be recognised as a valid time.

Example 
```deadline CS2113 IP submission /by 20201002 23:59```

Expected outcome:
```
Got it. I've added this task:
[D][✘] CS2113 IP submission (by: 20201002 23:59)
```

Example 
```deadline Get married /by 35 years old:)```

Expected outcome:
```
Got it. I've added this task:
[D][✘] Get married (by: 35 years old:))
```

### 3.4 List All: `list`
Display all the task, following chronological order of them being created.

Format: ```list```

Example 
```list```

Expected outcome:
```
1. [T][✘] read books
2. [E][✘] CS2113 lecture (at: 2020/09/21 1600)
```

### 3.5 Mark as Done: `done`
Mark a task as done.

Format: ```done <index>```

The index corresponds to the index of the task when they are
displayed in the list command. If the index falls out of the
range, no action will be performed.

Example 
```done 1```

Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] read books
```

### 3.6 Delete task: `delete`
Mark a task as done.

Format: ```delete <index>```

The index corresponds to the index of the task when they are
displayed in the list command. If the index falls out of the
range, no action will be performed.

Example 
```delete 1```

Expected outcome:
```
Got it. I've removed this task:
[T][✓] read books
```

### 3.7 Find by key phrases: `find`
Find all tasks with description that contains the query key phrase.

Format: ```delete <key phrase>```

The index displayed here does not correspond to the index of them
being created. Hence, **DO NOT USE** this index for `delete` command. 

Example 
```find lecture```

Expected outcome:
```
1. [E][✘] CS2113 lecture (at: 2020/09/21 1600)
```

### 3.8 Filter by date: `doneby`
Find all tasks which occurs/be done by a given date.

Format: ```doneby <Date-Time>```

This supports date time input of multiple format. [1]

If the input is not in the listed formats, no query will be performed.

If a task has no time specified, it will not be shown.

The index displayed here does not correspond to the index of them
being created. Hence, **DO NOT USE** this index for `delete` command. 

Example 
```doneby 20201230```

Expected outcome:
```
1. [E][✘] CS2113 lecture (at: 2020/09/21 1600)
```

### 3.9 Exit the program: `bye`

Exit the program.

Example 
```bye```

Expected outcome:
```
Bye. Hope to see you again soon!
```

## 4. Command Summary

|Command | Description|
|:------------:|:------------|
|todo | ```todo <todo description>```|
|event | ```event <event description> /at <date time>```|
|deadline | ```deadline <deadline description> /by <date time>```|
|list | ```list```|
|done | ```done <index>```|
|delete | ```delete <index>```|
|find | ```find <key phrase>```|
|doneby | ```doneby <date time>```|
|bye| ```bye```|

## 5. Notes
[1] Here shows all 12 valid formats.
    
    `yyyyMMdd HH:mm`
    `yyyy-MM-dd HH:mm`
    `yyyy MM dd HH:mm`
    `yyyy/MM/dd HH:mm`
    
    `yyyyMMdd HHmm`
    `yyyy-MM-dd HHmm`
    `yyyy MM dd HHmm`
    `yyyy/MM/dd HHmm`
    
    `yyyyMMdd`
    `yyyy-MM-dd`
    `yyyy MM dd`
    `yyyy/MM/dd`


