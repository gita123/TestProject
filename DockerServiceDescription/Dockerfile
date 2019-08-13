FROM maven:3.5.2-jdk-8

USER root

# Environment Setup
ENV no_proxy 'localhost, 127.0.0.1, 169.254.169.254, .iptho.co.uk, .ipttools.io, .ipttools.info, .svc.cluster.local, 10.200.0.1, 10.200.0.10'
ENV http_proxy 'http://proxy.local:8080'
ENV https_proxy 'http://proxy.local:8080'

# Install AWSCLI
RUN curl "https://s3.amazonaws.com/aws-cli/awscli-bundle.zip" -o "awscli-bundle.zip" &&\
    unzip awscli-bundle.zip &&\
    ./awscli-bundle/install -i /usr/local/aws -b /usr/local/bin/aws &&\
    rm -rf awscli-bundle.zip awscli-bundle

# Install Chrome Driver
RUN CHROMEDRIVER_VERSION=`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE` && \
	mkdir -p /opt/chromedriver-$CHROMEDRIVER_VERSION && \
	`curl -sS -o /tmp/chromedriver_linux64.zip http://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip` && \
	unzip -qq /tmp/chromedriver_linux64.zip -d /opt/chromedriver-$CHROMEDRIVER_VERSION && \
	rm /tmp/chromedriver_linux64.zip && \
	chmod +x /opt/chromedriver-$CHROMEDRIVER_VERSION/chromedriver && \
	ln -fs /opt/chromedriver-$CHROMEDRIVER_VERSION/chromedriver /usr/local/bin/chromedriver

# Install Chrome
RUN set -x && wget -qO - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN set -x && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" | tee /etc/apt/sources.list.d/google-chrome.list
RUN set -x && apt-get update
RUN set -x && apt-get -fy install google-chrome-stable

# Install kubectl
# Set the Kubernetes version as found in the UCP Dashboard or API
ARG k8sversion=v1.8.11
# Get the kubectl binary.
RUN set -x && curl -LO https://storage.googleapis.com/kubernetes-release/release/$k8sversion/bin/linux/amd64/kubectl
# Make the kubectl binary executable.
RUN set -x && chmod +x ./kubectl
# Move the kubectl executable to /usr/local/bin.
RUN set -x && mv ./kubectl /usr/local/bin/kubectl

# Install Python, pip and other dependencies needed for testAnalyser
RUN apt-get install python-pip -y && pip install requests python-jenkins pyyaml

# Install vim
RUN set -x && apt-get -y install vim

# Upgrade packages
RUN set -x && apt-get -y upgrade

#Add new user to execute tests
RUN set -x && groupadd javagroup
RUN set -x && useradd -g javagroup javauser -m

#Setup permission for local maven repository location and copy settings.xml into javauser's home
ADD Function/settings.xml /home/javauser/.m2/settings.xml
ADD Function/cacerts /home/javauser/.m2/cacerts
RUN set -x && chown javauser:javagroup -R /home/javauser/.m2/

# Copy Test Repository into the Docker Image
COPY . /app

# Update ownership for the local tests repository
RUN set -x && chown javauser:javagroup -R /app

# Switch user to javauser
USER javauser

WORKDIR /app/Function

# Download the dependencies while skipping the tests execution
RUN mvn clean install -Dmaven.test.skip=true

# Setup entry-point
RUN set -x && chmod +x /app/Function/docker-entrypoint.sh 
CMD /app/Function/docker-entrypoint.sh
