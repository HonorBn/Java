## 알고리즘 문제 풀이

<br>

### GitHub 사용법
* Git 설치
* Git Bash Path 지정
    ```
    $ vi .bashrc    // Insert 키 (편집 모드)
    $ cd "your directory"   // Esc 키
    $ :wq
    ```
* Git 로컬 초기화 / 확인
    ```
    $ git init
    $ git status
    ```
* Git 원격 저장소 지정
    ```
    $ git remote add origin https://github.com/HonorBn/Algorithm.git
    ```
* 파일 추가
    ```
    $ git add .
    ```
* 계정 확인
    ```
    $ git config -global user.email "ahnbnn@gmail.com"
    $ git config -global user.name "Youngbin-Ahn"
    ```
* Commit
    ```
    $ git commit -m "Comments"
    ```
* Push
    ```
    $ git push origin master
    ```

* 로컬 동기화
    ```
    $ git fetch origin
    $ git pull origin master
    ```
