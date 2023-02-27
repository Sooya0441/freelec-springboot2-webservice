#!/bin/bash
clear

# 프로젝트 디렉토리 주소는 스크립트 내에서 자주 사용하는 값이기 때문에 이를 변수로 저장합니다.
# 마찬가지로 PROJECT_NAME=freelec-springboot2-webservice도 동일하게 변수로 저장합니다.
# 쉘에서는 타입 없이 선언하여 저장합니다.
# 쉘에서는 $변수명으로 변수를 사용할 수 있습니다.
REPOSITORY=/home/ubuntu/app/step1
PROJECT_NAME=freelec-springboot2-webservice

# 제일 처음 git clone 받았던 디렉토리로 이동합니다.
# 바로 위희 웻 변수 설명을 따라 /home/ubuntu/app/step1/freelec-springboot2-webservice 주소로 이동합니다.
cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"
# 디렉토리 이동 후, master 브랜치의 최신 내용을 받습니다.
git pull

echo "> 프로젝트 Build 시작"
#프로젝트 내부의 gradlew로 bukld를 수행합니다.
./gradlew build

echo "> step1 디렉토리로 이동"
cd $REPOSITORY

echo "> Build 파일 복사"
# build의 결과물인 jar 피일을 복사해 jar 파일을 모아둔 위치로 복사합니다.
cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"
# 기존에 수행 중이던 스프링 부트 애플리케이션을 종료합니다.
# pgrep은 process id만 추출하는 명령어입니다.
# -f 옵션은 프로세스 이름으로 찾습니다.
CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "현재 구동중인 애플리케이션 pid: $CURRENT_PID"

# 현재 구동중인 프로세스가 있는지 없는지 판단해서 기능을 수행합니다.
# process id값을 보고 프로세스가 있으면 해당 프로세스르 종료합니다.
if [ -z "$CURRENT_PID" ]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 10
fi

echo "> 새 애플리케이션 배포"
# 새로 실행할 jar 파일명을 찾습니다.
# 여러 jar 파일이 생기기 때문에 tail -n로 가장 나중의 jar 파일(최신 파일)을 변수에 저장합니다.
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

# 찾은 jar 파일명으로 해당 jar  파일을 nohup으로 실행합니다.
# 스프링 부트의 장점으로 특별히 외장 톰캣을 설치할 필요가 없습니다.
# 내장 톰캣을 사용해서 jar파일만 있으면 바로 웹 애플리케이션 서버를 실행할 수 있습니다.
# 일반적으로 자바를 실행할 때는 java -jar라는 명령어를 사용하지만, 이렇게 하면 사용자가 터미널 접속을 끊을 때 애플리케이션도 같이 종료됩니다.
# 애플리케이션 실행자가 터미널을 종료해도 애플리케이션은 계속 구동될 수 있도록 nohup 명령어를 사용합니다.
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,/home/ubuntu/app/step1/application-oauth.properties,/home/ubuntu/app/step1/application-real-db.properties \
	-Dspring.profiles.active=real \
    $REPOSITORY/$JAR_NAME 2>&1 &
# -Dspring.config.location
# 스프링 설정 파일 위치를 지정합니다.
# 기본 옵션들을 담고 있는 application.properties와 OAuth 설정들을 담고 있는 application-oauth.properties의 위치를 지정합니다.
# classpath가 붙으면 jar안에 있는 resources 디렉토리를 기준으로 경로가 생성됩니다.
# application-oauth.properties는 절대 경로를 사용합니다. 외부에 파일이 있기 때문입니다.

# -Dspring.profiles.active=real
# application-real-db.properties 를 활성화시킵니다.
# application-real-db.properties 의 spring.profiles.include=oauth,real-db 옵션 때문에 real-db 역시 함께 활성화 대상에 포함됩니다.
sleep 15
tail -n 10 ./nohup.out
