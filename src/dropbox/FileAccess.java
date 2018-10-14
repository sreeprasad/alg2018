package dropbox;

import java.util.*;

/**
/A
|___ /B
    |___ /C <-- access
    |___ /D
|___ /E <-- access
    |___ /F
        |___ /G


folders = [
  ('A', None),
  ('B', 'A'),
  ('C', 'B'),
  ('D', 'B'),
  ('E', 'A'),
  ('F', 'E'),
]


access = set(['C', 'E'])

has_access(String folder_name) -> boolean

has_access("B") -> false
has_access("C") -> true
has_access("F") -> true
has_access("G") -> true

 */
public class FileAccess {


    /* Approach 1: construct a file system tree, with each file has an attribute isAccessible,
       search for the folder in the tree each time (BFS) -- start*/
    class FileNode {
        String fileName;
        List<FileNode> childFiles;
        boolean isAccessible; // default -> false

        FileNode(String fileName) {
            this.fileName = fileName;
        }
        // getters and setters
    }
    /* Approach 1: -- end */


    /* Approach 2: store accessible folders in a set, parent-child relationship in a map,
       find if ancestor is accessible -- start */
    private Set<String> accessibleFoldersSet;
    FileAccess(){
        this.accessibleFoldersSet = new HashSet<>();
    }

    public void set(String folderName) {
        accessibleFoldersSet.add(folderName);
    }

    public boolean hasAccess(String folderName) {
        String name = folderName;
        while (!accessibleFoldersSet.contains(name)) {
            name = getFather(name);
            if (name == null) {
                return false;
            }
        }
        return true;
    }

    private String getFather(String childName) {
        return "";
    }
    /* Approach 2: -- end */

}
