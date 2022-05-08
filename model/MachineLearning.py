import pickle

import numpy as np
import pandas as pd
from sklearn import ensemble
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.preprocessing import OneHotEncoder, PolynomialFeatures

pd.set_option("display.max_rows", None, "display.max_columns", None)

dataset = pd.read_csv("House price.csv")
# print(dataset['area'].value_counts())
# dataset = dataset.astype({'city': 'string', 'area': 'string'})
# print(dataset.head())
# print(dataset.describe())
# print(dataset.info())
# dataset.plot()
# plt.scatter(dataset.price, dataset.area)
# sns.pairplot(dataset)
# sns.heatmap(dataset.corr(), annot=True)
# plt.show()
# enc = OneHotEncoder(handle_unknown='ignore')
dum_df = pd.get_dummies(dataset, dtype=int)
# print(dum_df.head())
# plt.scatter(dum_df['bedrooms'],dum_df['bathrooms'])
# sns.heatmap(dum_df.corr(), annot=True)
# plt.show()
# print(dum_df.corr())
# print(dum_df.describe())
# print(dum_df.info())
# bridge_df = dataset.join(dum_df)
# print(dum_df)
X = dum_df.drop(columns="price")
y = dum_df["price"]
# print(dum_df.isna().sum())
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.30, random_state=1)
# print(X_test.info())
# ln = LinearRegression()
# ln.fit(X_train, y_train)
# rg = Ridge()
# rg.fit(X_train, y_train)
# ls = Lasso(alpha=0.015, fit_intercept=False, tol=0.00000001, max_iter=10000000, positive=True, normalize=True)
# ls.fit(X_train, y_train)
# ba = BayesianRidge()
# ba.fit(X_train, y_train)
#
# forest_reg = RandomForestRegressor(random_state=42)
# forest_reg.fit(X_train, y_train)
# model = ensemble.GradientBoostingRegressor()
# model.fit(X_train, y_train)
# coef_df = pd.DataFrame(ln.coef_, X.columns, columns=['coefficients'])
# print(coef_df)
# predictions = ln.predict(X_test)
# print(predictions)
# r_sq = ln.score(X_test, y_test)
# print('coefficient of determination:', r_sq)
# print('intercept:', ln.intercept_)
# print('slope:', ln.coef_)
# plt.scatter(y_test, predictions)
# sns.displot((y_test - predictions), bins=10)
# print(ln.score(X_train, y_train))
# print(rg.score(X_train, y_train))
# print(ls.score(X_train, y_train))
# print(ln.score(X_test, y_test))
# print(rg.score(X_test, y_test))
# print(ls.score(X_test, y_test))
# print('Random Forest R squared": %.4f' % forest_reg.score(X_test, y_test))
# print('Gradient Boosting R squared": %.4f' % model.score(X_test, y_test))
# y_pred = forest_reg.predict(X_test)
# forest_mse = mean_squared_error(y_pred, y_test)
# forest_rmse = np.sqrt(forest_mse)
# print('Random Forest RMSE: %.4f' % forest_rmse)
# y_pred = model.predict(X_test)
# model_mse = mean_squared_error(y_pred, y_test)
# model_rmse = np.sqrt(model_mse)
# print('Gradient Boosting RMSE: %.4f' % model_rmse)
# print("the testing dataset is:", X_test)
# print("the results for the test are:", ls.predict(X_test).tolist())
test = [10, 10, 2.0, 2.0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]
# print(model.predict([test]).tolist())
# X_test.to_csv("testing.csv")
# predicted_values = pd.DataFrame(ls.predict(X_test).tolist())
# predicted_values.to_csv("predicted.csv")
# plt.plot(np.log(predicted_values), color="green")
# plt.show()
#
# # EVALUATION
#
# # 1. Explained Variance Score
#
# print(cl('EXPLAINED VARIANCE SCORE:', attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('Explained Variance Score of OLS model is {}'.format(evs(y_test, ln.predict(X_test))), attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('Explained Variance Score of Ridge model is {}'.format(evs(y_test, rg.predict(X_test))), attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('Explained Variance Score of Gradient boosting model is {}'.format(evs(y_test, model.predict(X_test))),
#          attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('Explained Variance Score of random forest regressor model is {}'.format(
#     evs(y_test, forest_reg.predict(X_test))), attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('Explained Variance Score of Bayesian model  is {}'.format(evs(y_test, ba.predict(X_test))), attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# # 2. R-squared
#
# print(cl('R-SQUARED:', attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('R-Squared of OLS model is {}'.format(r2(y_test, ln.predict(X_test))), attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('R-Squared of Ridge model is {}'.format(r2(y_test, rg.predict(X_test))), attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('R-Squared of Gradient boosting model is {}'.format(r2(y_test, model.predict(X_test))), attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('R-Squared of random forest regressor model is {}'.format(r2(y_test, forest_reg.predict(X_test))),
#          attrs=['bold']))
#
# print('-------------------------------------------------------------------------------')
#
# print(cl('R-Squared of Bayesian model is {}'.format(r2(y_test, ba.predict(X_test))), attrs=['bold']))
#
# print('----------------------------------------------------------------------')

pr = PolynomialFeatures(degree=2)
X_train_pr = pr.fit_transform(X_train)
X_test_pr = pr.fit_transform(X_test)
# gbr = ensemble.GradientBoostingRegressor()
# gbr.fit(X_train_pr, y_train)
# print(gbr.predict(pr.fit_transform([test])).tolist())
# print(gbr.score(X_test_pr, y_test))
# print(evs(y_test, gbr.predict(X_test_pr)))
# print(r2(y_test, gbr.predict(X_test_pr)))
# pickle.dump(gbr, open('model.pkl', 'wb'))
# model = pickle.load(open('model.pkl', 'rb'))
# print(model.predict(pr.fit_transform([test])).tolist())
# plt.figure(figsize=(10, 7))
# feat_importances = pd.Series(gbr.feature_importances_)
# feat_importances.nlargest(7).plot(kind='barh')
# plt.show()
# score = []
#
# gsearch1 = ensemble.GradientBoostingRegressor(learning_rate=0.31, n_estimators=100, max_depth=10,
#                                                   min_samples_split=500, min_samples_leaf=30
#                                                   , subsample=0.8, random_state=10)
# gsearch1.fit(X_train_pr, y_train)
# score.append(gsearch1.score(X_test_pr, y_test))
# print( gsearch1.score(X_test_pr, y_test))
# pd.DataFrame(score).to_csv("scores.csv")
from sklearn.pipeline import Pipeline
from sklearn.compose import ColumnTransformer

X_new = dataset.drop(columns="price")
y_new = dataset["price"]
X_train_new, X_test_new, y_train_new, y_test_new = train_test_split(X_new, y_new, test_size=0.30, random_state=1)
numerical_features = ["bedrooms", "bathrooms", "size", "parking"]
numerical_transformer = PolynomialFeatures(degree=2)
categorical_features = ["city", "area"]
categorical_transformer = OneHotEncoder()
param_test1 = {'subsample': [0.6, 0.7, 0.75, 0.8, 0.85, 0.9]}
preprocessor = ColumnTransformer(transformers=[('cat', categorical_transformer, categorical_features),
                                               ('num', numerical_transformer, numerical_features)])
clf = Pipeline(steps=[('preprocessor', preprocessor), ('regressor',
                                                       GridSearchCV(
                                                           estimator=ensemble.GradientBoostingRegressor(
                                                               learning_rate=0.31,
                                                               n_estimators=100,
                                                               min_samples_split=500,
                                                               min_samples_leaf=50,
                                                               max_depth=8,
                                                               max_features='sqrt',
                                                               subsample=0.8,
                                                               random_state=10),
                                                           param_grid=param_test1, n_jobs=4, cv=5))])
print()
clf.fit(X_train_new, y_train_new)
print(clf.score(X_test_new, y_test_new))
print(clf.predict(pd.DataFrame([np.array(["Nairobi", "Muthaiga", 10, 10, 2, 5])],
                               columns=["city", "area", "bedrooms", "bathrooms", "size", "parking"])).tolist())
pickle.dump(clf, open('model.pkl', 'wb'))
model = pickle.load(open('model.pkl', 'rb'))
print(model.predict(pd.DataFrame([np.array(["Nairobi", "Muthaiga", 10, 10, 2, 5])],
                                 columns=["city", "area", "bedrooms", "bathrooms", "size", "parking"])).tolist())
model_columns = list(X_train_new.columns)
print(model_columns)
pickle.dump(model_columns, open('model_columns.pkl', 'wb'))
model = pickle.load(open('model_columns.pkl', 'rb'))