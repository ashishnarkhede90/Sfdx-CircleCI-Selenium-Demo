node_modules/sfdx-cli/bin/run force:org:create -v HubOrg -s -f config/project-scratch-def.json -a testorg -d 1

node_modules/sfdx-cli/bin/run force:mdapi:deploy -d md-src -w 5 -u testorg

node_modules/sfdx-cli/bin/run force:user:password:generate > /dev/null

ud=`sfdx force:user:display --targetusername testorg -v HubOrg --json`
un=$(echo $ud | jq '.result.username')
pw=$(echo $ud | jq '.result.password')

mkdir data
cat > data/credentials.properties <<EOF
    username=$un
    password=$pw
EOF