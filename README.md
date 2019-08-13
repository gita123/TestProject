# Prerequisites
## Following are required to execute the CTS on your local machine:
1. Jdk 8
2. Maven 3.5+
3. IDE (IntelliJ/Eclipse etc)
4. Git
5. web-browser drivers (chromedriver, geckodriver etc) must be compatible with the browser installed on your machine.
## Following would be required to probe kubernetes pods and helm charts
1. kubernetes-cli
2. kubernetes-helm
3. Read-Write Access to TTZO namespace in ops-kops1 cluster
## Following would be required to access the home-office network, git repository, kubernetes namespaces
1. gpg 
2. ssh  
3. Access to ops-tlg1 VPN.
4. Access to the Jenkins Instance.
5. Read write access to your tests Git repository.
6. Read write access to Jenkins seed job repository.

# Running the tests on your local machine
- Clone Or download the repository into your local machine on a preferred location.
- cd common-starter-helm/Function
- mvn clean verify
## Viewing the test report:
- cd target/site/reports/serenity/
- open index.html in your browser

# Please follow the below confluence pages for more details:
[Deploying the Common Test Framework on a Local 
Machine](https://confluence.ipttools.info/display/CTF/Deploying+the+Common+Test+Framework+on+a+Local+Machine?src=contextnavpagetreemode)

[Deploying the Common Test Framework into TTZO](https://confluence.ipttools.info/display/CTF/Deploying+the+Common+Test+Framework+into+TTZO?src=contextnavpagetreemode)
