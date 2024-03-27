Strategy for Constructing Acceptable Heuristic Solutions
To construct an acceptable heuristic function h(n), we remove some constraints from our initial problem, thus creating a 'relaxed' problem. The value of the heuristic function h(n) expresses how far a state n is from the closest Goal State (GS). The heuristic value is an estimation of the actual distance of node n from the nearest GS. In our problem, finding a(n) for the relaxed problem is equivalent to finding h(n).

Accordingly, based on the above:

Let's suppose we are in a random state and want to determine if it belongs to the final state (sorted list).

If the random state is identical to the final state (sorted list), then: h(n) = 0
However, if the random state does not match the final state (sorted list):
I compare it with each state of the set (children) of the random state and count how many positions it differs (the child state from the final state). Therefore, in the 'relaxed' version of the problem, from all the states I compare with my random state, I retain the count of differences with each one, and finally return the maximum count of differences.
h(n) = max(h1(n), ..., hk(n)).
Furthermore, because for each state n the value h(n) <= a(n) (real distance of n from the nearest GS), our heuristic function is acceptable.
