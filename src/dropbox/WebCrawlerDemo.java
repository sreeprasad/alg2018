package dropbox;

import java.util.*;

/*
How to stop a thread:
let run() end
 */
class WebCrawlerResource {
	Queue<String> queue = new LinkedList<>();
	Set<String> set = new HashSet<>();
	boolean flag = false;

	public synchronized void add() throws InterruptedException {
		while (flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " Interrupted");
				throw e;
			}

		}
		List<String> links = Helper.getLinks();
		if (links != null) {
			for (String link : links) {
				if (!set.contains(link)) {
					queue.offer(link);
					set.add(link);
					System.out.println(Thread.currentThread().getName() + "+++++add " + link);
				}
			}
		}
		flag = true;
		notifyAll();
	}

	public synchronized void populate() throws InterruptedException {
		while (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " Interrupted");
				throw e;
			}
		}
		if (!queue.isEmpty()) {
			System.out.println(Thread.currentThread().getName() + "-------pop " + queue.poll());
		}
		flag = false;
		notifyAll();
	}
}

class Input implements Runnable {

	private WebCrawlerResource resource;
	private boolean stop;

	Input (WebCrawlerResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				resource.add();
			} catch (InterruptedException e) {
				stop = true;
			}
		}
	}
}

class Output implements Runnable {

	private WebCrawlerResource resource;
	private boolean stop;

	Output (WebCrawlerResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {

		while (!stop) {
			try {
				resource.populate();
			} catch (InterruptedException e) {
				stop = true;
			}
		}
	}
}

public class WebCrawlerDemo {
	public static void main(String[] args) {
		WebCrawlerResource r = new WebCrawlerResource();

		Input input = new Input(r);
		Output output = new Output(r);

		Thread t1 = new Thread(input);
		Thread t2 = new Thread(input);
		Thread t3 = new Thread(output);
		Thread t4 = new Thread(output);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		while (true) {
			if (r.set.size() > 100) {
				t1.interrupt();
				t2.interrupt();
				t3.interrupt();
				t4.interrupt();
				System.out.println("Over");
				break;
			}
		}

	}
}

class Helper {
	private static int i = 0;
	static List<String> getLinks() {
		if (i < 5000) {
			return Arrays.asList(String.valueOf(i++),String.valueOf(i++),
					String.valueOf(i++),String.valueOf(i++),String.valueOf(i++));
		} else {
			return null;
		}
	}
}