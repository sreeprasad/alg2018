package dropbox;

import java.util.*;

// Reference: 1point3acres thread 467473
public class WebCrawler2 implements Runnable {
	Queue<String> queue = new LinkedList<>();
	Set<String> visited = new HashSet<>();

	public void run() {
		while(true) {
			List<String> urls = Helper.getLinks();
			synchronized(this) {
				if (urls != null) {
					for (String newUrl : urls) {
						if (!visited.contains(newUrl)) {
							queue.offer(newUrl);
							visited.add(newUrl);
							System.out.println("url added: " + newUrl);
						}
					}
				}
//				notifyAll();
				System.out.println("2--" + Thread.currentThread().getName());
			}
			String nextUrl;
			synchronized(this) {
				while(queue.isEmpty()) {
					try {
						wait(50000);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				nextUrl = queue.poll();
				if (nextUrl == null) {
					break;
				}
				System.out.println("url populated: " + nextUrl);
				System.out.println("1--" + Thread.currentThread().getName());
			}

		}
	}
}
class WebCrawler2Demo {
	public static void main(String[] args) {
		WebCrawler2 wc2 = new WebCrawler2();
		Thread t1 = new Thread(wc2);
		Thread t2 = new Thread(wc2);
		t1.start();
		t2.start();
	}
}