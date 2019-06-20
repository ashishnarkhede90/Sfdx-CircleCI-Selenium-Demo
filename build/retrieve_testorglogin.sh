un=$USERNAME
pw=$PASSWORD
mkdir data
cat > data/creds.properties <<EOF
        username=$un
        password=$pw
EOF

cat data/creds.properties