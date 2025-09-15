# Charli

*A minimal task assistant with a pop persona.*

---

## What is Charli?

Charli is a lightweight desktop chatbot that helps you track todos, deadlines, and events. It supports tagging, search, and autosave to a local file. 

### Features at a glance

* **Todos / Deadlines / Events**
* **Tag / Untag** tasks (duplicates prevented)
* **List / Find / Delete** tasks
* **Autosave** to `./data/charli.txt`
* **Graceful error handling** for invalid commands and missing data files

---

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/charli/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output from the GUI: 
   ```
   Hi British Vogue!
   What club classics would you like on your summer rotation?
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

---

## Quick Start

When Charli starts, type commands into the input box and press **Enter**.

Example session:

```
rotation
bop 360 
drop Palette /by 18/12/2025 2359
show The Chase /from Fri 7pm /to Fri 10pm
find Palette
tag 1 #indiepop
untag 1 #indiepop
played 1
unplayed 1
delete 1
bye
```

---

## Data File

* Location: `./data/charli.txt`

