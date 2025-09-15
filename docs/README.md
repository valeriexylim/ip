# Charli User Guide

![Product Screenshot](Ui.png)

Charli is a lightweight desktop chatbot that helps you track todos, deadlines, and events. It supports tagging, search, and autosave to a local file.

---
### Quick start

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

## Feature: Adding todos (bops)

Describe the action: Adds a simple todo without a date.

**Example:** `bop <description>`

**Example usage:**

```
bop buy projector tickets
```

**Expected outcome:**

```
Sweet! Added this bop:
[T][ ] buy projector tickets

Now you have 2 tracks in your rotation!
```

---

## Adding deadlines (drops)

Describe the action: Adds a deadline task that appears in the list and is saved to the data file. Dates must be in `dd/mm/yyyy HHmm`.

**Example:** `drop <description> /by <dd/mm/yyyy HHmm>`

**Example usage:**

```
drop Palette /by 19/09/2025 2359
```

**Expected outcome:**

```
Sweettt! Added this upcoming drop:
[D][ ] Palette (by: 19 Sep 2025, 11:59PM)

Now you have 1 tracks in your rotation!
```

---

## Feature: Adding shows (events)

Describe the action: Adds an event with free‑form `/from` and `/to` strings.

**Example:** `show <title> /from <string> /to <string>`

**Example usage:**

```
show The Chase /from Fri 7pm /to Fri 10pm
```

**Expected outcome:**

```
Let’s rave! Added this event:
[E][ ] The Chase (from: Fri 7pm, to: Fri 10pm)

Now you have 3 tracks in your rotation!
```

---

## Feature: Tagging tasks

Describe the action: Add or remove tags from a task. Tags are normalized to start with `#`; duplicates are ignored.

**Add tags** — `tag <index> <tag1>`

```
tag 2 urgent
```

**Expected outcome:**

```
Tagged!
[T][ ] buy projector tickets [#urgent]
```

**Remove tags** — `untag <index> <tag1>`

```
untag 2 urgent
```

**Expected outcome:**

```
Untagged!
[T][ ] buy projector tickets
```

---

## Feature: List and Find

Describe the action: Show all tasks, or filter by keyword in description.

**List** — `rotation`

```
1.[T][ ] buy projector tickets [#school]
2.[D][ ] Palette (by: 19 Sep 2025, 11:59PM)
3.[E][ ] Hackathon (from: Sat 9am, to: Sun 6pm)
```

**Find** — `find <keyword>`

```
find iP
```

**Expected outcome:**

```
Found 1 match:
2.[D][ ] CS2103T iP (by: 19 Sep 2026, 11:59PM)
```

---

## Feature: Mark played / unplayed

Describe the action: Mark a task done (played) or not done (unplayed).

**Mark played** — `played <index>`

```
played 1
```

**Expected outcome:**

```
Marked as played:
[T][X] 360
```

**Mark unplayed** — `unplayed <index>`

```
unplayed 1
```

**Expected outcome:**

```
Marked as unplayed:
[T][ ] 360
```

---

## Feature: Delete

Describe the action: Delete a task by its 1‑based index.

**Delete** — `delete <index>`

```
delete 1
```

**Expected outcome:**

```
Alright, I removed this from your rotation:
[T][ ] 360
```
