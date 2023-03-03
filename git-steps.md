to update the git repo

1: make changes in your repo

2:
git add -A
//adds the changes to be staged

3:
git commit -m "a message you want people in the future will see"
//bundles staged changes into a commit


4:
git checkout -b ABranchNameYouPick
//gives a branch alias to your commit, this allows for reviewing changes online

5:
git push -u origin ABranchNameYouPick
//pushes the commit into the cloud repo aka origin. It will make you use the same name from the previous step.

6:
Go onto GitHub. It should alert you that you pushed a branch and you can make a Pull Request.  Click that.

7: review the Pull Request and if it's what you want you can Merge it