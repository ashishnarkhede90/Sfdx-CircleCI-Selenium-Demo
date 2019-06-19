#!/bin/zsh
echo "Running X virtual framebuffer"
Xvfb :0 -ac &
export DISPLAY=:99 

un=$USERNAME
pw=$PASSWORD
mkdir data
cat > data/credentials.json <<EOF
	{
	 "username": $un,
	 "password": $pw
	}
EOF

cat data/credentials.json