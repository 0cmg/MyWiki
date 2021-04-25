### 剑指offer

#### 1、二维数组中的查找

 题目描述

在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

[

 [1,2,8,9],
 [2,4,9,12],
 [4,7,10,13],
 [6,8,11,15]

]

给定 target = 7，返回 true。

给定 target = 3，返回 false。

示例1

输入

```
7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
```

返回值

```
true
```

说明

```
存在7，返回true
```

示例2

输入

```
3,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
```

返回值

```
false
```

说明

```
不存在3，返回false
```

解题

```java
思路一：
public class Solution {
    public boolean Find(int target, int [][] array) {
        int m = array.length;
        if(m <= 0){
            return false;
        }
        for(int []num : array){
            for(int i : num){
                if(target == i){
                    return true;
                }
            }
        }
        return false;
    }
}
```

```java
思路二：
public class Solution {
    public boolean Find(int target, int [][] array) {
        int m = array.length;
        if(m <= 0){
            return false;
        }
        int n = array[0].length;
        if(n <= 0){
            return false;
        }
        int r =0 , c = n-1;
        while(r < m && c >= 0){
            if(target == array[r][c]){
                return true;
            }else if(target > array[r][c]){
                r++;
            }else{
                --c;
            }
        }
        return false;
    }
}
```



#### 2、替换空格

题目描述

请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

示例1

输入

```
"We Are Happy"
```

返回值

```
"We%20Are%20Happy"
```

解题

```java
思路一：
import java.util.*;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param s string字符串 
     * @return string字符串
     */
    public String replaceSpace (String s) {
        if("".equals(s)){
            return "";
        }
        return s.replace(" ", "%20");
    }
}
```

#### 3、从头到尾打印链表

题目描述

输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

示例1

输入

```
{67,0,24,58}
```

返回值

```
[58,24,0,67]
```

解题

```java
package com.cmg.javabase.offer;

import java.util.ArrayList;

public class PrintList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(11);
        ListNode l2 = new ListNode(12);
        ListNode l3 = new ListNode(13);
        ListNode l4 = new ListNode(14);
        ListNode l5 = new ListNode(15);
        ListNode l6 = new ListNode(16);
        l1.setNext(l2);
        l2.setNext(l3);
        l3.setNext(l4);
        l4.setNext(l5);
        l5.setNext(l6);
        ArrayList<Integer> list = printListFromTailToHead(l1);
        System.out.println(list);
    }
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        if(listNode != null){
            list.add(listNode.val);
            ListNode node = listNode.next;
            while(node != null){
                list.add(node.val);
                node = node.next;
            }
        }
        int size = list.size();
        if(size > 0){
            for(int i=size-1;i>=0; i--){
                list2.add(list.get(i));
            }
        }
        return list2;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
```

#### 4、重建二叉树

题目描述 

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

示例1

输入

```
[1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
```

返回值

```
{1,2,5,3,4,6,7}
```

#### 5、数组中出现次数超过一半的数字

题目描述

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

示例1

输入

```
[1,2,3,2,2,2,5,4,2]
```

返回值

```
2
```

解题

```java
public static int MoreThanHalfNum_2(int []numbers) {
        if(numbers == null){
            return 0;
        }
        int len = numbers.length;
        if(len <= 0){
            return 0;
        }
        int count = len/2 + 1;
        for(int i : numbers){
            int co = 0;
            for(int j : numbers){
                if(i == j){
                    co++;
                }
            }
            if(co >= count){
                return i;
            }
        }
        return 0;
    }
```

#### 6、最小的K个数

题目描述

给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组

示例1

输入

```
[4,5,1,6,2,7,3,8],4 
```

返回值

```
[1,2,3,4]
 
```

解题

```java
public static ArrayList<Integer> GetLeastNumbers_2(int [] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(input == null){
            return arrayList;
        }
        int len = input.length;
        if(len <= 0 || len < k){
            return arrayList;
        }
        for(int m=0;m<len-1;m++){
            for(int n=0;n<m;n++){
                if(input[m] < input[n]){
                    int temp = input[m];
                    input[m] = input[n];
                    input[n] = temp;
                }
            }
        }
        for(int i=0;i<len;i++){
            if(i <= k){
                arrayList.add(input[i]);
            }
        }
        return arrayList;
    }
```

#### 7、连续子数组的最大和

题目描述

输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为 O(n).

示例1

输入

```
[1,-2,3,10,-4,7,2,-5]
```

返回值

```
18
```

说明

```
输入的数组为{1,-2,3,10,—4,7,2,一5}，和最大的子数组为{3,10,一4,7,2}，因此输出为该子数组的和 18。 
```

解题

```

```

#### 8、整数中1出现的次数（从1到n整数中1出现的次数）

题目描述

求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。

示例1

输入

```
13
```

返回值

```
6
```

解题

```java
public static int NumberOf1Between1AndN_1(int n) {
        int count = 0;
        for(int i=1;i<=n;i++){
            char []ch = String.valueOf(i).toCharArray();
            for(char c : ch){
                if('1' == c){
                    count++;
                }
            }
        }
        return count;
    }
```

#### 9、把数组排成最小的数

题目描述

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

示例1

输入

```
[3,32,321]
```

返回值

```
"321323"
```

#### 10、丑数

题目描述

把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

示例1

输入

```
7
```

返回值

```
8
```

#### 11、第一个只出现一次的字符

题目描述

在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）

示例1

输入

```
"google"
```

返回值

```
4
```

解题

```java
public static int FirstNotRepeatingChar(String str){
        if(str == null || "".equals(str)){
            return -1;
        }
        int len = str.length();
        if(len <= 0 || len > 10000){
            return -1;
        }
        for(char c : str.toCharArray()){
            int lastIndex = str.lastIndexOf(c);
            int firstIndex = str.indexOf(c);
            if(lastIndex == firstIndex){
                return firstIndex;
            }
        }
        return -1;
    }
```

#### 12、数组中的逆序对

题目描述

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007


对于50\%50%的数据,size\leq 10^4*s**i**z**e*≤104
对于75\%75%的数据,size\leq 10^5*s**i**z**e*≤105
对于100\%100%的数据,size\leq 2*10^5*s**i**z**e*≤2∗105

输入描述:

```
题目保证输入的数组中没有的相同的数字
```

示例1

输入

```
[1,2,3,4,5,6,7,0]
```

返回值

```
7
```

#### 13、两个链表的第一个公共结点

题目描述

输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）

解题

```java
public static ListNode FindFirstCommonNode ( ListNode pHead1 , ListNode pHead2 ) {
        ListNode node1 = pHead1;
        ListNode node2 = pHead2;
        if(node1 == null || node2 == null){
            return null;
        }
        while(node1 != node2){
            node1 = node1 == null ? pHead1 : node1.next;
            node2 = node2 == null ? pHead2 : node2.next;
        }
        return node1;
    }
```

#### 14、数字在升序数组中出现的次数

题目描述

统计一个数字在升序数组中出现的次数。

示例1

输入

```
[1,2,3,3,3,3,4,5],3
```

返回值

```
4
```

解题

```java
public static int GetNumberOfK_2(int [] array , int k) {
        if(array == null || array.length <=0 || k < array[0] || k > array[array.length-1]){
            return 0;
        }
        int cnt =0;
        for(int i : array){
            if(i == k){
                cnt++;
            }
        }
        return cnt;
    }
```

#### 15、二叉树的深度

题目描述

输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

示例1

输入

```
{1,2,3,4,5,#,6,#,#,7}
```

返回值

```
4
```