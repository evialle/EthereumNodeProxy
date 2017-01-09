#!/usr/bin/env bash

CMD aws s3 cp --region ${REGION} s3://${BUCKET}/eth-${network}-blockchain eth/datadir/eth-${network}-blockchain.rlp

