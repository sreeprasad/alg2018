package LinkedIn;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214086
 * intersection and union of two list（sorted），注意参数给的是List<Integer> ，不知道实现的话get方法可能是o（n）的。
 * 一开始写了个用get的，doubt 我的时间复杂度，我说这个可能是o（n），那可能需要用iterator。
 * 考官说对那就写吧，然后秒写，这里楼主就突然傻逼了。这题相当于peeking iterator，双指针（双iterator）遍历两个list时，
 * iter.hasNext()是不能作为跳出循环的依据的，因为有一个元素是缓存起来的，只有当那个缓存的头元素也用完了之后才能跳出循环。
 * 所以这个bug卡了十分钟都没查出来，其实非常明显的但就是先入为主觉得这怎么可能错没有注意检查这个边界条件。最后给了个test case我才恍然卧槽，
 * 这智商真是坑了坑了。
 *
 * 建议再有这种要在iterator上搞peek的，不要嫌费事，写一个peekiterator的类包装一下原来的iter。
 * 然后时间空间复杂度再问
 * 写完了这个，问union多个list的话怎么办，我说那就merge吧，面试官说不行这个空间复杂度很高。我说那就priorityqueue吧，面试官表示满意，
 * 问了下空间时间复杂度。然后继续follow up问那就俩list union，你搞个parallel算法。楼主蒙了一下，想了两分钟，说那就第一个数组分n份，
 * 找到pivotal点在第二个数组上二分搜索该元素对应的位置，得到这些位置传给并行算法就可以了吧。面试官说行，问了下空间复杂度（我觉得并没有卵区别，还是和以前一样）。
 *
 *
 * https://www.1point3acres.com/bbs/thread-454153-1-1.html
 * union： 传进来的二个sorted array,  有duplicate。不让预处理array，说array很长。
 * intersection：说传进来的可能是linked list，要用iterator。next()写。
 */
public class SortedListIntersectionAnUnion {

	// intersection of two lists - common values
	public static List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
		List<Integer> intersectionList = new ArrayList<>();
		ListIterator<Integer> iter1 = list1.listIterator();
		ListIterator<Integer> iter2 = list2.listIterator();

		boolean hold = false;
		int v2 = 0; // value in list2

		while (iter1.hasNext()) {
			int v1 = iter1.next();
			while (iter2.hasNext()) {
				if (!hold) {
					v2 = iter2.next();
				}
				if (v2 == v1) {
					intersectionList.add(v1);
					hold = false;
					break;
				}
				if (v2 > v1) {
					hold = true;
					break;
				}
			}
		}
		return intersectionList;
	}

	// union two/k sorted lists, using priority queue, add one element if duplicate
	public static List<Integer> union(List<Integer> list1, List<Integer> list2) {
		List<Integer> unionList = new ArrayList<>();
		ListIterator<Integer> iter1 = list1.listIterator();
		ListIterator<Integer> iter2 = list2.listIterator();

		boolean hold = false;
		int v2 = 0; // value in list2

		while (iter1.hasNext()) {
			int v1 = iter1.next();
			while (iter2.hasNext()) {
				if (!hold) {
					v2 = iter2.next();
				}
				if (v2 <= v1) {
					if (unionList.size() == 0 || v1 != unionList.get(unionList.size() - 1)) {
						unionList.add(v1);
					}
					hold = false;
					break;
				}
				hold = true;
				break;
			}
			if (unionList.size() == 0 || v1 != unionList.get(unionList.size() - 1)) {
				unionList.add(v1);
			}
		}
		while (iter2.hasNext()) {
			v2 = iter2.next();
			if (unionList.size() == 0 || v2 != unionList.get(unionList.size() - 1)) {
				unionList.add(v2);
			}
		}
		return unionList;
	}

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(1,2,3,3,3,4));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(3,3,4,5,6));
		System.out.println(intersection(list1, list2));
		System.out.println(union(list1, list2));
	}
}
