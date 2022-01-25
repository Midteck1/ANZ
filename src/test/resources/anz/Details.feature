@tag
Feature: A person with following details

  Background: Logged in
    Given I am navigation to ANZ site
	@Details
  Scenario: Enter following details
    When Enter following details
      | NoDependents | AnnualIncome | AnnualOtherIncome | LivingExp | CurrentHLRepa | OtherLP | OtherComm | TotalCC |
      |            0 |        80000 |             10000 |       500 |             0 |     100 |         0 |   10000 |
    Then Click StartOver and clear the form
    And Enterning only for living expenses and click Work out how much I could borrow button
    Then verfiy the message 