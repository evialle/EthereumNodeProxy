FROM openjdk:8u102-jdk

WORKDIR /workir
VOLUME /tmp

# install tools and dependencies
RUN apt-get update && \
    apt-get install -y \
        g++ \
        curl \
        git \
        file \
        python \
        python-pip \
        binutils

RUN pip install awscli

### Install Parity

##Install Parity Engine
# install rustup
RUN curl https://sh.rustup.rs -sSf | sh -s -- -y
# rustup directory
ENV PATH /root/.cargo/bin:$PATH
# show backtraces
ENV RUST_BACKTRACE 1
# show tools
RUN rustc -vV && cargo -V && gcc -v &&  g++ -v

# build parity
RUN git clone https://github.com/ethcore/parity && \
        cd parity && \
        git checkout stable && \
        git pull && \
        cargo build --release --verbose && \
        ls /build/parity/target/release/parity && \
        strip /build/parity/target/release/parity

RUN file /build/parity/target/release/parity

## Install Bloc kchain
#Download Blockhain
CMD aws s3 cp --region ${REGION} s3://${BUCKET}/eth-${network}-blockchain eth/datadir/eth-${network}-blockchain.rlp
#Import Blockchain
CMD parity import eth/datadir/eth-${network}-blockchain.rlp
#Start Parity
#TODO add options
CMD ["/build/parity/target/release/parity"]

## Install Crontab for backup

# Add crontab file in the cron directory
ADD sh/crontab /etc/cron.d/backup_blockchain_cron
# Give execution rights on the cron job
RUN chmod 0644 /etc/cron.d/backup_blockchain_cron
# Create the log file to be able to run tail
RUN touch /var/log/cron.log
# Run the command on container startup
CMD cron && tail -f /var/log/cron.log


### Install Ethereum Proxy
ADD *.jar ethereum-proxy.jar
RUN sh -c 'touch /ethereum-proxy.jar'
EXPOSE 80