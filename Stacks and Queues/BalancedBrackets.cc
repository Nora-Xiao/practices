#include <bits/stdc++.h>

using namespace std;

// Complete the isBalanced function below.
string isBalanced(string s) {
    stack<char> stack; 
    // opening bracket往stack里放，closing bracket时 把stack里最新的那个拿出来看看
    for (char const c : s) {
        if (c == '{' || c == '[' || c == '(') {
            stack.push(c);
        } else { // closing bracket
            if (stack.empty()) {
                return "NO";
            }

            char last = stack.top();
            stack.pop();

            // 以下三种情况，任一成立，都要return no
            bool brace = (last == '{' && c != '}');
            bool bracket = (last == '[' && c != ']');
            bool paren = (last == '(' && c != ')');
            if (brace || bracket || paren) {
                return "NO";
            }            
        }
    }

    if (stack.empty()) {
        return "YES";
    } else {
        return "NO";
    }
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int t;
    cin >> t;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int t_itr = 0; t_itr < t; t_itr++) {
        string s;
        getline(cin, s);

        string result = isBalanced(s);

        fout << result << "\n";
    }

    fout.close();

    return 0;
}
