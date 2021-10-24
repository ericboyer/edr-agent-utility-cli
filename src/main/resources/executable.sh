#!/bin/sh

echo Hello, EDR! The command $(basename "$0") was invoked at $(date) with args: $1 $2 | tee /tmp/hello.log