#!/bin/bash
#
#  LICENSE
#
#  This file is part of Flyve MDM Inventory Library for Android.
#
#  Inventory Library for Android is a subproject of Flyve MDM. Flyve MDM is a 
#  mobile device management software.
#
#  Flyve MDM is free software: you can redistribute it and/or
#  modify it under the terms of the GNU General Public License
#  as published by the Free Software Foundation; either version 3
#  of the License, or (at your option) any later version.
#
#  Flyve MDM Inventory Library for Android is distributed in the hope that it 
#  will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  --------------------------------------------------------------------------------
#  @author    Rafael Hernandez - <rhernandez@teclib.com>
#  @author    Naylin Medina    - <nmedina@teclib.com>
#  @copyright Copyright (c) Teclib'
#  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
#  @link      https://github.com/flyve-mdm/android-inventory-library/
#  @link      http://flyve.org/android-inventory-library/
#  @link      https://flyve-mdm.com/
#  --------------------------------------------------------------------------------
#

GH_COMMIT_MESSAGE=$(git log --pretty=oneline -n 1 $CIRCLE_SHA1)

if [[ $GH_COMMIT_MESSAGE != *"ci(release): generate CHANGELOG.md for version"* && $GH_COMMIT_MESSAGE != *"ci(build): release version"* ]]; then

# run generatedocumentation script
ci/scripts/ci_generate_documentation.sh

# Update layouts and styles of development folder for correct display on project site

# get gh-pages branch
git fetch origin gh-pages

# move to gh-pages
git checkout gh-pages

# clean workspace
sudo git clean -fdx

# remove default stylesheet.css
sudo rm ./development/code-documentation/stylesheet.css
sudo rm ./development/coverage/resources/report.css
sudo rm ./development/test-reports/css/base-style.css
sudo rm ./development/test-reports/css/style.css

# add new css
cp ./css/codeDocumentation.css ./development/code-documentation/stylesheet.css
cp ./css/coverage.css ./development/coverage/resources/report.css
cp ./css/testReports.css ./development/test-reports/css/style.css
touch ./development/test-reports/css/base-style.css

# change headers
ruby ci/add_header.rb

# add and commit changes
git add . && git commit -m "docs(development): update headers and css styles"

# push to branch
git push origin gh-pages

# go back to original branch
git checkout $CIRCLE_BRANCH

fi