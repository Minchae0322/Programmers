
class Solution {

    int[] result;
    int lastContinuitySuccess = 0;

    int firstHealth;

    public int solution(int[] bandage, int health, int[][] attacks) {
        int lastAttackTime = attacks[attacks.length - 1][0];
        result = new int[lastAttackTime + 1];
        int[] attackTime = checkAttack(attacks, lastAttackTime);
        firstHealth = health;

        for (int i = 0; i <= lastAttackTime; i++) {
            if (attackTime[i] != 0) {
                lastContinuitySuccess = 0;
                health -= attackTime[i];
                if (health <= 0) {
                    return -1;
                }
            } else {
                int plusHealthNum = bandage[1];
                lastContinuitySuccess++;
                if (lastContinuitySuccess == bandage[0]) {
                    lastContinuitySuccess = 0;
                    plusHealthNum += bandage[2];
                }
                health = Math.min(firstHealth, health + plusHealthNum);

            }
        }
        int answer = health;

        return answer;
    }

    private int[] checkAttack(int[][] attacks, int lastAttackTime) {
        int[] attackTime = new int[lastAttackTime + 1];

        for (int i = 0; i < attacks.length; i++) {
            attackTime[attacks[i][0]] = attacks[i][1];
        }
        return attackTime;
    }
}