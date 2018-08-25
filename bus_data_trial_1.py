import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

dataset = pd.read_csv('data/Bus Route Trip Data.csv')
data_new = dataset.dropna()

# Dropping Identifiers
data_new.drop(['Operation date', 'VehicleNo'], axis=1, inplace=True)

places = data_new['RouteName'].values.tolist()
unique_places = set(places)
print(unique_places)
places = list(unique_places)

# 569 unique values

# Loading E-Ticket Data
e_ticket_dataset = pd.read_excel('data/Passenger e-ticket data/Report_01_JUL_2018.xlsx')
e_ticket_dataset.isnull().sum()

# There is no NaN in the dataset
users = e_ticket_dataset['User Count'].values.tolist()
unique_count = set(users)
unique_count = list(unique_count)

# Creating a Price per Head Column
e_ticket_dataset['Price per Head'] = e_ticket_dataset['Amount']/e_ticket_dataset['User Count']

# Dropping Amount and User Count
e_ticket_dataset.drop(['Amount', 'User Count'], axis=1, inplace=True)

# Count of RouteNames
places = e_ticket_dataset['Routename'].values.tolist()
unique_places = set(places)
print(unique_places)
places = list(unique_places)

data_new['comfortlevel'] = np.random.randint(1,5) 

