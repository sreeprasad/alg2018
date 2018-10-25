package Airbnb;


import java.util.HashMap;
import java.util.Map;

/**
 * create(path, value), set(path, value), get(path), watch(path, callback)
 * 文件系统，每个文件夹或者文件有一个具体的数值，设计一个类，给定key可以update val可以insert val, 要求可以处理exception
 *
 * Example
 * create("/a",1)
 * get("/a") //得到1
 * create("/a/b",2)
 * get("/a/b") //得到2
 * create("/c/d",1) //Error，因为它的上一级“/c”并不存在
 * get("/c") //Error,因为“/c”不存在
 *
 * follow up是写一个watch函数，比如watch("/a",new Runnable(){System.out.println("helloword");})后，
 * 每当create("/a/b"，1) 等在/a之下的目录不产生error的话，都会执行绑在“/a”上的callback函数
 *
 * 比如 watch("/a",System.out.println("yes"))
 * watch("/a/b",System.out.println("no"))
 * 当create("/a/b/c",1)时，两个callback函数都会被触发，会output yes 和no
 *
 * ***********
 *
 * NOTE: 这里用的Runnable会一直运行不停，Runnable用得不熟表示不知道怎么停下来，就用System.exit(0)测试了
 *
 * ***********
 *
 * Follow Up：是写一个watch函数，比如watch("/a",new Runnable(){System.out.println(“helloword”);})后，
 * 每当create("/a/b"，1) 等在/a之下的目录不产生error的话，都会执行绑在“/a”上的callback函数
 *
 * 比如 watch("/a",System.out.println(“yes”))
 * watch("/a/b",System.out.println(“no”))
 * 当create("/a/b/c",1)时，两个callback函数都会被触发，会output yes 和no.
 *
 * 我对java的callback并不是很熟悉，面试官小哥很好地给了trigger callback的接口，因为之前在地里看到说没必要建trie，所以直接hashmap解决，但处理字符串找它的上一层目录处理“/”比较容易出bug，要注意判断根目录的情况
 */
public class FileSystem {

    private Map<String, Integer> pathMap;
    private Map<String, Runnable> watchMap;

    public FileSystem() {
        pathMap = new HashMap<>();
        pathMap.put("", -1); // in case of "/a"

        watchMap = new HashMap<>();
    }

    public boolean create(String path, Integer value) {
        // should we validate input?
        if (pathMap.containsKey(path)) {
            return false;
        }
        String parentPath = path.substring(0, path.lastIndexOf("/"));
        if (!pathMap.containsKey(parentPath)) {
            return false;
        }

        // callback logic start
        int indexOfPrevSlash = path.indexOf('/');
        int indexOfCurIndex = path.substring(indexOfPrevSlash + 1).indexOf('/') == -1 ? -1 : indexOfPrevSlash + 1 + path.substring(indexOfPrevSlash + 1).indexOf('/');
        while (indexOfCurIndex != -1) {
            String p = path.substring(0, indexOfCurIndex);
            if (watchMap.containsKey(p)) {
                watchMap.get(p).run();
            }
            indexOfPrevSlash = indexOfCurIndex;
            indexOfCurIndex = path.substring(indexOfPrevSlash + 1).indexOf('/') == -1 ? -1 : indexOfPrevSlash + 1 + path.substring(indexOfPrevSlash + 1).indexOf('/');
        }
        // callback logic end

        pathMap.put(path, value);
        return true;
    }

    public boolean set(String path, Integer value) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        pathMap.put(path, value);
        return true;
    }

    public Integer get(String path) {
        // what to do if path does not exist? throw exception?
        return pathMap.get(path);
    }

    public void watch(String path, Runnable callback) {
        if (!pathMap.containsKey(path)) {
            // do we need to do anything? do we validate path?
            return;
        }
        watchMap.put(path, callback);
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.create("/a", 1));
        System.out.println(fs.create("/a/b", 2));
        System.out.println(fs.set("/b/c", 3));
        System.out.println(fs.set("/a/b", 4));
        System.out.println(fs.get("/a/b"));
        fs.watch("/a", fs.new RunnableImpl());
        fs.watch("/a/b", fs.new RunnableImpl());
        System.out.println(fs.create("/a/b/c", 5));
    }

    class RunnableImpl implements Runnable {
        @Override
        public void run() {
            System.out.println("runnable.");
        }
    }

}
