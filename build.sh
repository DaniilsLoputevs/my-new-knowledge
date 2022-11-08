#!/usr/bin/env bash
promt "123"
cd echo dirname || exit
cd echo '000-bash' || exit
cd "$(dirname "$0")/docker/storage" || exit
cd echo '123-bash' || exit

GIT_BRANCH_NAME=${"GIT_BRANCH_NAME--1"}
export GIT_BRANCH_NAME


#docker build -t daniilsloputevs/my-new-knowledge-1:GIT_BRANCH_NAME  &&
#docker push daniilsloputevs/my-new-knowledge-1