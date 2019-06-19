echo "1. Reading private key..."
if [ -z $SERVER_KEY ]
then
	echo "Private key missing..."
	exit 1
else
	echo "Private key... OK"		
	echo $SERVER_KEY | base64 -d > server.key
fi

echo "2. Reading username"
if [ -z $USERNAME ]
then
	echo "Username missing..."
	exit 1
else
	echo "Username... OK"
fi

echo "3. Reading client Id"
if [ -z $CONSUMERKEY ]
then
	echo "Client Id missing..."
	exit 1
else
	echo "Client Id... OK"
	echo "4. Authenticating to Dev Hub"

	# Invoke SFDX command to log in to the Dev Hub org
	node_modules/sfdx-cli/bin/run force:auth:jwt:grant --clientid $CONSUMERKEY --jwtkeyfile server.key --username $USERNAME --setdefaultdevhubusername -a HubOrg	
fi


