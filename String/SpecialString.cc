#include <bits/stdc++.h>

using namespace std;

// Complete the substrCount function below.
long substrCount(int n, string s) {
    long count = 0;

    // loop over the string, count单个char、双个、三个
    int continuous_count [n];
    int i = 0;
    while (i < n) {
        char first = s[i];

        int j = i + 1;
        // count有几个连续的
        while (j < n && s[j] == first) {
            j++;
        }

        // 有几个连续的
        continuous_count[i] = j - i;
        // 假如有3个连续的，就是1个3 char substring，2个2 char substring, 3个3 char substring
        // 1 + 2 + 3 + ... + n = n * (n + 1) / 2
        count += (continuous_count[i] * (continuous_count[i] + 1)) >> 1;
        i = j;
    }

    // 中间有一个char，左右两边有相同chars
    // loop over每一个potential的中间char
    for (i = 1; i <= n - 2; i++) {
        // 左右char同，中间的一个不同
        if (s[i - 1] == s[i + 1] && s[i - 1] != s[i]) {
            count += min(continuous_count[i - 1], continuous_count[i + 1]);
        }

        // 若左边的char == 现在的char，现在的char无continuous_count，需要在这里补一下
        if (s[i - 1] == s[i]) {
            continuous_count[i] = continuous_count[i - 1];
        }
    }

    return count;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int n;
    cin >> n;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string s;
    getline(cin, s);

    long result = substrCount(n, s);

    fout << result << "\n";

    fout.close();

    return 0;
}
