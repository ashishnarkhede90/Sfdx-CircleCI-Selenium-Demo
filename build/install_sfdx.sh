#!/bin/zsh

export SFDX_USE_GENERIC_UNIX_KEYCHAIN=true
export SFDX_DOMAIN_RETRY=300
npm install sfdx-cli
node_modules/sfdx-cli/bin/run --version
node_modules/sfdx-cli/bin/run plugins --core