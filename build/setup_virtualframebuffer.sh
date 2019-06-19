#!/bin/zsh
echo "Running X virtual framebuffer"
Xvfb :0 -ac &
export DISPLAY=:99 