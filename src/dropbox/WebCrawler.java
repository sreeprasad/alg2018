package dropbox;

import java.util.*;

public class WebCrawler implements Runnable {

	private String state = "processing";
	private String domain = "";

	private Queue<String> queue;
	private Set<String> record; // result

	WebCrawler (String domain) {
		this.domain = domain;
		queue = new LinkedList<>();
		record = new HashSet<>();
		queue.offer(domain);
		record.add(domain);
	}

	public void run() { // have to use synchronized keyword to use wait() and notify(), otherwise get exception

		while (!queue.isEmpty()) {
			try {Thread.sleep(1000);} catch(InterruptedException e) {}
			synchronized (this) { // lock for where queue and record are being modified, synchronized method is also using this object's lock
				if (!queue.isEmpty()) {
					String curUrl = queue.poll();
					System.out.println(Thread.currentThread().getName() + " " + curUrl);
					for (String url : getLinks(curUrl)) {
						if (record.contains(url)) {
							continue;
						}
						queue.offer(url);
						record.add(url);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		WebCrawler wc = new WebCrawler("http://www.google.com");
		Thread t1 = new Thread(wc);
		Thread t2 = new Thread(wc);
		Thread t3 = new Thread(wc);
		Thread t4 = new Thread(wc);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

	/**
	 * helper class
	 * @param url current url
	 * @return return all sub-urls under current url
	 */
	public List<String> getLinks(String url) {
		return new ArrayList<>(Arrays.asList("www.leetcode.com", "www.facebook.com", "www.dropbox.com"));
	}
}
