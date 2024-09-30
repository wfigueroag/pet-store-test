#language
  #encoding:UTF-8
  #Author:William Figueroa

@AllFeatures
Feature: Validate all requests about Pet resource

  @AddNewPetToStore
  Scenario Outline: Validate POST method when a user wants to create a new pet
    Given I have the next data:
      | id           | <id>           |
      | name         | <name>         |
      | idCategory   | <idCategory>   |
      | nameCategory | <nameCategory> |
      | photoUrl     | <photoUrl>     |
      | idTags       | <idTags>       |
      | nameTags     | <nameTags>     |
      | status       | <status>       |
    When I execute de service of create a new pet
    Then the response will be 200
    And its schema will be ok with the structure schemas/answerCreateNewPet.json
    Examples:
      | id | name | idCategory | nameCategory | photoUrl                                   | idTags | nameTags       | status    |
      | 77 | Fish | 20         | Water        | http://FishPhoto.com,http://FishPhoto2.com | 1,2,3  | Tag1,Tag2,Tag3 | Available |

  @UpdatePetToStore
  Scenario Outline: Validate PUT method when a user wants to update a pet
    Given I have the next data:
      | id           | <id>           |
      | name         | <name>         |
      | idCategory   | <idCategory>   |
      | nameCategory | <nameCategory> |
      | photoUrl     | <photoUrl>     |
      | idTags       | <idTags>       |
      | nameTags     | <nameTags>     |
      | status       | <status>       |
    When I execute de service of update a pet
    Then the response will be 200
    And its schema will be ok with the structure schemas/answerCreateNewPet.json
    Examples:
      | id | name | idCategory | nameCategory | photoUrl                                    | idTags | nameTags       | status    |
      | 77 | Fish | 20         | Water        | http://FishPhoto3.com,http://FishPhoto4.com | 1,2,3  | Tag1,Tag2,Tag3 | Available |

  @FindByStatus
  Scenario Outline:  Validate GET method when the user wants to get pet by status
    Given I have the next data:
      | status | <status> |
    When I execute de service to get pet by status
    Then the response will be 200
    And its schema will be ok with the structure schemas/answerGetPetByStatus.json
    Examples:
      | status    |
      | available |

  @FindByTags
  Scenario Outline:  Validate GET method when the user wants to get pet by tag
    Given I have the next data:
      | tags | <tags> |
    When I execute de service to get pet by tag
    Then the response will be 200
    And its schema will be ok with the structure schemas/answerGetPetByStatus.json
    Examples:
      | tags   |
      | string |

  @FindByID
  Scenario Outline:  Validate GET method when the user wants to get pet by id
    Given I have the next data:
      | id | <id> |
    When I execute de service to get pet by id
    Then the response will be 200
    And its schema will be ok with the structure schemas/answerCreateNewPet.json
    Examples:
      | id |
      | 3  |

