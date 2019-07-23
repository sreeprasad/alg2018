import utils.PrintArray;

import java.util.*;

public class SmallestSufficientTeam {
	// DP
	public int[] smallestSufficientTeamDP(String[] req_skills, List<List<String>> people) {

		int sLen = req_skills.length;
		int pLen = people.size();

		Map<String, Integer> skills = new HashMap<>();
		for (int i = 0; i < sLen; i++) {
			skills.put(req_skills[i], i);
		}

		// dp array, size is all possibilities of skill set combinations of a team - 2^16
		// Long is the bit representation of people - 60 bits
		// index: skillSet; value: team members
		Long[] dp = new Long[1 << sLen];
		dp[0] = 0L;

		// people loop
		for (int i = 0; i < pLen; i++) {

			// generate bit representation for each people's skill set
			List<String> pSkills = people.get(i);
			int pSkillSet = 0;
			for (String skill : pSkills) {
				pSkillSet |= (1 << skills.get(skill));
			}

			// skill set combination loop
			for (int curSkillSet = 0; curSkillSet < dp.length; curSkillSet++) {

				if (dp[curSkillSet] == null) continue;

				int newSkillSet = curSkillSet | pSkillSet;
				if (newSkillSet == curSkillSet) continue; // empty skill set or no change

				if (dp[newSkillSet] == null || Long.bitCount(dp[newSkillSet]) > Long.bitCount(dp[curSkillSet]) + 1) {
					dp[newSkillSet] = dp[curSkillSet] | (1L << i); // add people to team
				}
			}
		}

		Long team = dp[(1 << sLen) - 1];
		int[] result = new int[Long.bitCount(team)]; // number of people in the team
		int index = 0;
		for (int i = 0; i < pLen; i++) {
			if (((1L << i) & team) != 0) {
				result[index++] = i;
			}
		}

		return result;
	}

	// DFS
	public int[] smallestSufficientTeamDFS(String[] req_skills, List<List<String>> people) {

		int sLen = req_skills.length;
		int pLen = people.size();

		Map<String, Integer> skills = new HashMap<>();
		for (int i = 0; i < sLen; i++) {
			skills.put(req_skills[i], i);
		}

		int[] skillSets = new int[pLen];
		for (int i = 0; i < pLen; i++) {
			// generate bit representation for each people's skill set
			List<String> pSkills = people.get(i);
			int pSkillSet = 0;
			for (String skill : pSkills) {
				pSkillSet |= (1 << skills.get(skill));
			}
			skillSets[i] = pSkillSet;
//			System.out.println(Integer.toBinaryString(pSkillSet));
		}

		long[] smallestTeam = new long[1];
		smallestTeam[0] = (1L << pLen) - 1;

		dfs(skillSets, 0, 0, 0, smallestTeam, sLen);

		int[] result = new int[Long.bitCount(smallestTeam[0])];
		int index = 0;
		for (int i = 0; i < pLen; i++) {
			if ((smallestTeam[0] & (1L << i)) != 0) {
				result[index++] = i;
			}
		}
		return result;
	}

	public void dfs(int[] skillSets, int peopleIndex, int curSkillSet, long curTeam, long[] smallestTeam, int sLen) {

		if (curSkillSet == (1 << sLen) - 1) {
			if (Long.bitCount(curTeam) < Long.bitCount(smallestTeam[0])) {
				smallestTeam[0] = curTeam;
				System.out.println(Long.toBinaryString(curTeam) + " " + Long.toBinaryString(curSkillSet));
			}
			return;
		}

		if (Long.bitCount(curTeam) + 1 >= Long.bitCount(smallestTeam[0])) return; // prune

		for (int i = peopleIndex; i < skillSets.length; i++) {

			int newSkillSet = curSkillSet | skillSets[i];
			long newTeam = curTeam | (1L << i);

			if (newSkillSet == curSkillSet) continue;

			dfs(skillSets, i + 1, newSkillSet, newTeam, smallestTeam, sLen);

			// if (Long.bitCount(newTeam) >= Long.bitCount(smallestTeam[0])) break; // same as the line with comment "prune"
		}
	}

	public static void main(String[] args) {
		String[] req_skills = {"hkyodbbhr","p","biflxurxdvb","x","qq","yhiwcn"};
		String[][] peopleArr = {{"yhiwcn"},{},{},{},{"biflxurxdvb","yhiwcn"},{"hkyodbbhr"},{"hkyodbbhr","p"},{"hkyodbbhr"},{},{"yhiwcn"},{"hkyodbbhr","qq"},{"qq"},{"hkyodbbhr"},{"yhiwcn"},{},{"biflxurxdvb"},{},{"hkyodbbhr"},{"hkyodbbhr","yhiwcn"},{"yhiwcn"},{"hkyodbbhr"},{"hkyodbbhr","p"},{},{},{"hkyodbbhr"},{"biflxurxdvb"},{"qq","yhiwcn"},{"hkyodbbhr","yhiwcn"},{"hkyodbbhr"},{},{},{"hkyodbbhr"},{},{"yhiwcn"},{},{"hkyodbbhr"},{"yhiwcn"},{"yhiwcn"},{},{},{"hkyodbbhr","yhiwcn"},{"yhiwcn"},{"yhiwcn"},{},{},{},{"yhiwcn"},{},{"yhiwcn"},{"x"},{"hkyodbbhr"},{},{},{"yhiwcn"},{},{"biflxurxdvb"},{},{},{"hkyodbbhr","biflxurxdvb","yhiwcn"},{}};
		List<List<String>> people = new ArrayList<>();
		for (String[] peopleSkill : peopleArr) {
			people.add(Arrays.asList(peopleSkill));
		}

		SmallestSufficientTeam sst = new SmallestSufficientTeam();
		int[] resultDP = sst.smallestSufficientTeamDP(req_skills, people);
		int[] resultDFS = sst.smallestSufficientTeamDFS(req_skills, people);
		System.out.println("DP: "); PrintArray.printArray(resultDP);
		for (int i: resultDP) {PrintArray.printStringArray(peopleArr[i]);}
		System.out.println("DFS: "); PrintArray.printArray(resultDFS);
		for (int i: resultDFS) {PrintArray.printStringArray(peopleArr[i]);}
	}
}
