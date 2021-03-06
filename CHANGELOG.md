
<a name="4.0.1"></a>
## [4.0.1](https://github.com/masgeek/akilimo-api/compare/4.0.0...4.0.1)

> 2021-05-13

### Docs

* updated changelog file

### Fix

* corrected invalid sms message generation
* fixed properties mapping name path

### Pull Requests

* Merge pull request [#161](https://github.com/masgeek/akilimo-api/issues/161) from masgeek/fix/sms
* Merge pull request [#159](https://github.com/masgeek/akilimo-api/issues/159) from masgeek/fix/messaging


<a name="4.0.0"></a>
## [4.0.0](https://github.com/masgeek/akilimo-api/compare/3.9.0...4.0.0)

> 2021-05-11

### Ci

* change docker image name
* updated circleci build workflow to generate api and migration images
* changed auto aproval actor to check for repo owner
* removed uneeded python code formaytter [@black](https://github.com/black)
* added dicumentation in precomit config file
* added new hook and rearranged the order

### Docs

* disabled PR templated momentarily
* update PR template
* Updated github PR template and CODEOWNERS file
* Updated changelog file

### Feat

* added migrations for sp and triggers
* added app request stats endpoint
* added endpoint for fetching request statistics
* added excluded paths to web security
* added migration to add unique constrains to authority table
* Added endpoints for adding user
* added migration for auth database
* added endpoint security
* add migrations
* migrations for app request stats

### Fix

* fixed null value due to incorrect key property reference
* added user feedback to security exception
* corrected migration script names to match migration file xml names
* made functions migrations rerunnable
* corrected trigger calls naming
* added missing migration for stats view updates
* fixed view migration
* fixed jdbs database driver
* added app request stats filter for easy filtering
* added excluded flag to stats view script
* added scripts to updated null and empty columns in app report table
* added better query sorting
* removed swagger annotations
* corrected docker build sequence and steps
* fixed invalid package names that prevented bean generation
* updated application config files
* modularized migrations to independently running module
* fixed invalid reference

### Perf

* updated library dependencies

### Refactor

* added runalways tag to view migration
* added exception throwing when record is not found
* removed timestamper.sh file
* add new git hook
* modularization or project
* fixed table encoding formart in migration
* revised sql view script path name
* added new Enum values

### Pull Requests

* Merge pull request [#158](https://github.com/masgeek/akilimo-api/issues/158) from masgeek/fix/null-tzs-rate
* Merge pull request [#157](https://github.com/masgeek/akilimo-api/issues/157) from masgeek/fix/security-exception
* Merge pull request [#156](https://github.com/masgeek/akilimo-api/issues/156) from masgeek/fix/triggers
* Merge pull request [#155](https://github.com/masgeek/akilimo-api/issues/155) from masgeek/fix/missing-migration
* Merge pull request [#154](https://github.com/masgeek/akilimo-api/issues/154) from masgeek/fix/view-migration
* Merge pull request [#153](https://github.com/masgeek/akilimo-api/issues/153) from masgeek/fix/dependencies
* Merge pull request [#152](https://github.com/masgeek/akilimo-api/issues/152) from masgeek/feature/feedback-endpoint
* Merge pull request [#151](https://github.com/masgeek/akilimo-api/issues/151) from masgeek/doc/pr-template
* Merge pull request [#150](https://github.com/masgeek/akilimo-api/issues/150) from masgeek/feature/new-migrations
* Merge pull request [#148](https://github.com/masgeek/akilimo-api/issues/148) from masgeek/fix/docker-build
* Merge pull request [#147](https://github.com/masgeek/akilimo-api/issues/147) from masgeek/chore/refactor
* Merge pull request [#145](https://github.com/masgeek/akilimo-api/issues/145) from masgeek/feature/app-use-case-report

### BREAKING CHANGE


















Modularization

database migrations


<a name="3.9.0"></a>
## [3.9.0](https://github.com/masgeek/akilimo-api/compare/3.8.3...3.9.0)

> 2021-04-28

### Ci

* added new todo generator
* changed target build branch
* added todo github actions

### Docs

* Updated pull request template style

### Feat

* added migration for user type column

### Pull Requests

* Merge pull request [#144](https://github.com/masgeek/akilimo-api/issues/144) from masgeek/feature/user-feedback-rev
* Merge pull request [#142](https://github.com/masgeek/akilimo-api/issues/142) from masgeek/feat/changelog


<a name="3.8.3"></a>
## [3.8.3](https://github.com/masgeek/akilimo-api/compare/3.8.2...3.8.3)

> 2021-04-19

### Pull Requests

* Merge pull request [#140](https://github.com/masgeek/akilimo-api/issues/140) from masgeek/fix/user-feedback


<a name="3.8.2"></a>
## [3.8.2](https://github.com/masgeek/akilimo-api/compare/3.8.1...3.8.2)

> 2021-03-03

### Pull Requests

* Merge pull request [#138](https://github.com/masgeek/akilimo-api/issues/138) from masgeek/fix/missing_fertilizer


<a name="3.8.1"></a>
## [3.8.1](https://github.com/masgeek/akilimo-api/compare/3.8.0...3.8.1)

> 2021-03-02

### Pull Requests

* Merge pull request [#136](https://github.com/masgeek/akilimo-api/issues/136) from masgeek/fix/price-and-migration


<a name="3.8.0"></a>
## [3.8.0](https://github.com/masgeek/akilimo-api/compare/3.7.1...3.8.0)

> 2021-03-02

### Pull Requests

* Merge pull request [#134](https://github.com/masgeek/akilimo-api/issues/134) from masgeek/feature/user-feedback


<a name="3.7.1"></a>
## [3.7.1](https://github.com/masgeek/akilimo-api/compare/3.7.0...3.7.1)

> 2021-02-14

### Pull Requests

* Merge pull request [#132](https://github.com/masgeek/akilimo-api/issues/132) from masgeek/fix/gender-property


<a name="3.7.0"></a>
## [3.7.0](https://github.com/masgeek/akilimo-api/compare/3.6.3...3.7.0)

> 2021-02-11

### Pull Requests

* Merge pull request [#131](https://github.com/masgeek/akilimo-api/issues/131) from masgeek/fix/main-activity
* Merge pull request [#129](https://github.com/masgeek/akilimo-api/issues/129) from masgeek/fix/wrong-import-order


<a name="3.6.3"></a>
## [3.6.3](https://github.com/masgeek/akilimo-api/compare/3.6.2...3.6.3)

> 2021-02-02

### Pull Requests

* Merge pull request [#128](https://github.com/masgeek/akilimo-api/issues/128) from masgeek/fix/missed-import-file
* Merge pull request [#126](https://github.com/masgeek/akilimo-api/issues/126) from masgeek/fix/prices


<a name="3.6.2"></a>
## [3.6.2](https://github.com/masgeek/akilimo-api/compare/3.6.1...3.6.2)

> 2021-01-19

### Pull Requests

* Merge pull request [#124](https://github.com/masgeek/akilimo-api/issues/124) from masgeek/fix/prices
* Merge pull request [#123](https://github.com/masgeek/akilimo-api/issues/123) from masgeek/fix/currency-payload
* Merge pull request [#122](https://github.com/masgeek/akilimo-api/issues/122) from masgeek/fix/default-fertilizer
* Merge pull request [#121](https://github.com/masgeek/akilimo-api/issues/121) from masgeek/feature/pagination
* Merge pull request [#118](https://github.com/masgeek/akilimo-api/issues/118) from masgeek/fix/translated-units


<a name="3.6.1"></a>
## [3.6.1](https://github.com/masgeek/akilimo-api/compare/3.6.0...3.6.1)

> 2020-08-25

### Pull Requests

* Merge pull request [#116](https://github.com/masgeek/akilimo-api/issues/116) from masgeek/fix/empty-fertilizer-list


<a name="3.6.0"></a>
## [3.6.0](https://github.com/masgeek/akilimo-api/compare/3.5.1...3.6.0)

> 2020-06-26

### Pull Requests

* Merge pull request [#114](https://github.com/masgeek/akilimo-api/issues/114) from masgeek/fix/revert-yaramila


<a name="3.5.1"></a>
## [3.5.1](https://github.com/masgeek/akilimo-api/compare/3.5.0...3.5.1)

> 2020-06-24

### Pull Requests

* Merge pull request [#113](https://github.com/masgeek/akilimo-api/issues/113) from masgeek/fix/revert-yaramila
* Merge pull request [#111](https://github.com/masgeek/akilimo-api/issues/111) from masgeek/fix/remove-yaramila


<a name="3.5.0"></a>
## [3.5.0](https://github.com/masgeek/akilimo-api/compare/3.4.0...3.5.0)

> 2020-06-22

### Pull Requests

* Merge pull request [#110](https://github.com/masgeek/akilimo-api/issues/110) from masgeek/fix/null-plumber-response
* Merge pull request [#109](https://github.com/masgeek/akilimo-api/issues/109) from masgeek/fix/sms-errors
* Merge pull request [#107](https://github.com/masgeek/akilimo-api/issues/107) from masgeek/feature/added-country-price


<a name="3.4.0"></a>
## [3.4.0](https://github.com/masgeek/akilimo-api/compare/3.3.2...3.4.0)

> 2020-06-18

### Pull Requests

* Merge pull request [#105](https://github.com/masgeek/akilimo-api/issues/105) from masgeek/feature/added-country-price


<a name="3.3.2"></a>
## [3.3.2](https://github.com/masgeek/akilimo-api/compare/3.3.1...3.3.2)

> 2020-06-04

### Pull Requests

* Merge pull request [#103](https://github.com/masgeek/akilimo-api/issues/103) from masgeek/fix/sms-content-validation


<a name="3.3.1"></a>
## [3.3.1](https://github.com/masgeek/akilimo-api/compare/3.3.0...3.3.1)

> 2020-06-03

### Pull Requests

* Merge pull request [#101](https://github.com/masgeek/akilimo-api/issues/101) from masgeek/fix/language


<a name="3.3.0"></a>
## [3.3.0](https://github.com/masgeek/akilimo-api/compare/3.2.0...3.3.0)

> 2020-05-24

### Pull Requests

* Merge pull request [#99](https://github.com/masgeek/akilimo-api/issues/99) from masgeek/feature/sweet-potato-prices


<a name="3.2.0"></a>
## [3.2.0](https://github.com/masgeek/akilimo-api/compare/3.1.1...3.2.0)

> 2020-05-20

### Pull Requests

* Merge pull request [#98](https://github.com/masgeek/akilimo-api/issues/98) from masgeek/feature/new-fertilizer
* Merge pull request [#97](https://github.com/masgeek/akilimo-api/issues/97) from masgeek/fix/wring-fertilizer_name
* Merge pull request [#95](https://github.com/masgeek/akilimo-api/issues/95) from masgeek/feature/new-fertilizer


<a name="3.1.1"></a>
## [3.1.1](https://github.com/masgeek/akilimo-api/compare/3.1.0...3.1.1)

> 2020-05-16

### Pull Requests

* Merge pull request [#93](https://github.com/masgeek/akilimo-api/issues/93) from masgeek/fix/handle-http-errors


<a name="3.1.0"></a>
## [3.1.0](https://github.com/masgeek/akilimo-api/compare/3.0.0...3.1.0)

> 2020-05-15

### Pull Requests

* Merge pull request [#91](https://github.com/masgeek/akilimo-api/issues/91) from masgeek/feature/sms-sending


<a name="3.0.0"></a>
## [3.0.0](https://github.com/masgeek/akilimo-api/compare/2.0.1...3.0.0)

> 2020-05-11

### Pull Requests

* Merge pull request [#90](https://github.com/masgeek/akilimo-api/issues/90) from masgeek/feature/dynamic-variables
* Merge pull request [#89](https://github.com/masgeek/akilimo-api/issues/89) from masgeek/fix/rate-limit
* Merge pull request [#87](https://github.com/masgeek/akilimo-api/issues/87) from masgeek/feature/demo-endpoint


<a name="2.0.1"></a>
## [2.0.1](https://github.com/masgeek/akilimo-api/compare/2.0.0...2.0.1)

> 2020-05-11

### Pull Requests

* Merge pull request [#85](https://github.com/masgeek/akilimo-api/issues/85) from masgeek/fix/lazy-classes


<a name="2.0.0"></a>
## [2.0.0](https://github.com/masgeek/akilimo-api/compare/1.2.0...2.0.0)

> 2020-04-24

### Pull Requests

* Merge pull request [#83](https://github.com/masgeek/akilimo-api/issues/83) from masgeek/feature/add-maiz-prices-column


<a name="1.2.0"></a>
## [1.2.0](https://github.com/masgeek/akilimo-api/compare/1.1.2...1.2.0)

> 2020-04-24

### Pull Requests

* Merge pull request [#81](https://github.com/masgeek/akilimo-api/issues/81) from masgeek/fix/add-db-column


<a name="1.1.2"></a>
## [1.1.2](https://github.com/masgeek/akilimo-api/compare/1.1.1...1.1.2)

> 2020-04-20

### Pull Requests

* Merge pull request [#78](https://github.com/masgeek/akilimo-api/issues/78) from masgeek/masgeek-patch-2
* Merge pull request [#77](https://github.com/masgeek/akilimo-api/issues/77) from masgeek/masgeek-patch-1


<a name="1.1.1"></a>
## [1.1.1](https://github.com/masgeek/akilimo-api/compare/1.1.0...1.1.1)

> 2020-04-20

### Pull Requests

* Merge pull request [#75](https://github.com/masgeek/akilimo-api/issues/75) from masgeek/fix/sorting-index
* Merge pull request [#74](https://github.com/masgeek/akilimo-api/issues/74) from masgeek/master
* Merge pull request [#73](https://github.com/masgeek/akilimo-api/issues/73) from masgeek/develop
* Merge pull request [#70](https://github.com/masgeek/akilimo-api/issues/70) from masgeek/develop
* Merge pull request [#67](https://github.com/masgeek/akilimo-api/issues/67) from masgeek/develop
* Merge pull request [#65](https://github.com/masgeek/akilimo-api/issues/65) from masgeek/develop


<a name="1.1.0"></a>
## [1.1.0](https://github.com/masgeek/akilimo-api/compare/1.0.0...1.1.0)

> 2020-04-16

### Pull Requests

* Merge pull request [#72](https://github.com/masgeek/akilimo-api/issues/72) from masgeek/feature/indexer-for-caprice


<a name="1.0.0"></a>
## [1.0.0](https://github.com/masgeek/akilimo-api/compare/0.9.0...1.0.0)

> 2020-04-16

### Pull Requests

* Merge pull request [#71](https://github.com/masgeek/akilimo-api/issues/71) from masgeek/fix/invalid-docker-group
* Merge pull request [#69](https://github.com/masgeek/akilimo-api/issues/69) from masgeek/feature/adde-exact-cass-price


<a name="0.9.0"></a>
## [0.9.0](https://github.com/masgeek/akilimo-api/compare/0.8.0...0.9.0)

> 2020-04-16

### Pull Requests

* Merge pull request [#68](https://github.com/masgeek/akilimo-api/issues/68) from masgeek/fix/avg-price-property
* Merge pull request [#66](https://github.com/masgeek/akilimo-api/issues/66) from masgeek/feature/cassava-prices-endpoint


<a name="0.8.0"></a>
## [0.8.0](https://github.com/masgeek/akilimo-api/compare/0.7.1...0.8.0)

> 2020-03-28

### Pull Requests

* Merge pull request [#64](https://github.com/masgeek/akilimo-api/issues/64) from masgeek/fix/conflicts


<a name="0.7.1"></a>
## [0.7.1](https://github.com/masgeek/akilimo-api/compare/0.7.0...0.7.1)

> 2020-03-26


<a name="0.7.0"></a>
## [0.7.0](https://github.com/masgeek/akilimo-api/compare/0.6.2...0.7.0)

> 2020-03-16


<a name="0.6.2"></a>
## [0.6.2](https://github.com/masgeek/akilimo-api/compare/0.6.1...0.6.2)

> 2020-03-13


<a name="0.6.1"></a>
## [0.6.1](https://github.com/masgeek/akilimo-api/compare/0.6.0...0.6.1)

> 2020-03-10


<a name="0.6.0"></a>
## [0.6.0](https://github.com/masgeek/akilimo-api/compare/0.5.1...0.6.0)

> 2020-03-09


<a name="0.5.1"></a>
## [0.5.1](https://github.com/masgeek/akilimo-api/compare/0.5.0...0.5.1)

> 2020-03-09


<a name="0.5.0"></a>
## [0.5.0](https://github.com/masgeek/akilimo-api/compare/0.4.1...0.5.0)

> 2020-03-02


<a name="0.4.1"></a>
## [0.4.1](https://github.com/masgeek/akilimo-api/compare/0.4.0...0.4.1)

> 2020-02-19


<a name="0.4.0"></a>
## [0.4.0](https://github.com/masgeek/akilimo-api/compare/0.3.0...0.4.0)

> 2020-02-19


<a name="0.3.0"></a>
## [0.3.0](https://github.com/masgeek/akilimo-api/compare/0.2.0...0.3.0)

> 2019-12-02


<a name="0.2.0"></a>
## [0.2.0](https://github.com/masgeek/akilimo-api/compare/0.1.2...0.2.0)

> 2019-11-28


<a name="0.1.2"></a>
## [0.1.2](https://github.com/masgeek/akilimo-api/compare/0.1.1...0.1.2)

> 2019-11-15


<a name="0.1.1"></a>
## [0.1.1](https://github.com/masgeek/akilimo-api/compare/0.1.0...0.1.1)

> 2019-11-12


<a name="0.1.0"></a>
## 0.1.0

> 2019-11-12
