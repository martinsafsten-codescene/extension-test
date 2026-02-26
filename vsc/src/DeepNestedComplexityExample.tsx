/**
 * Deep Nested Complexity Example - React component with excessive nesting
 * This makes the code hard to read, test, and maintain
 */

import React from 'react';

interface User {
  id: string;
  name: string;
  role: string;
  permissions: string[];
  subscription?: {
    tier: string;
    active: boolean;
    expiresAt: Date;
  };
  profile?: {
    address?: {
      country: string;
      state?: string;
      city?: string;
    };
  };
}

interface Product {
  id: string;
  name: string;
  price: number;
  stock: number;
  category: string;
}

export const DeepNestedComplexityExample: React.FC<{
  user: User | null;
  products: Product[];
  isLoading: boolean;
  error: Error | null;
}> = ({ user, products, isLoading, error }) => {
  return (
    <div className="container">
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <div>
          {error ? (
            <div>Error: {error.message}</div>
          ) : (
            <div>
              {user ? (
                <div>
                  {user.subscription ? (
                    <div>
                      {user.subscription.active ? (
                        <div>
                          {user.subscription.tier === "premium" ? (
                            <div>
                              <h1>Welcome Premium Member: {user.name}</h1>
                              {user.permissions.includes("admin") ? (
                                <div>
                                  <div className="admin-panel">
                                    <h2>Admin Controls</h2>
                                    {products.length > 0 ? (
                                      <div>
                                        {products.map((product) => (
                                          <div key={product.id}>
                                            {product.stock > 0 ? (
                                              <div>
                                                {product.category === "electronics" ? (
                                                  <div>
                                                    {product.price > 1000 ? (
                                                      <div className="high-value-product">
                                                        <h3>{product.name}</h3>
                                                        <p>Price: ${product.price}</p>
                                                        {user.profile ? (
                                                          <div>
                                                            {user.profile.address ? (
                                                              <div>
                                                                {user.profile.address.country === "US" ? (
                                                                  <div>
                                                                    {user.profile.address.state ? (
                                                                      <div>
                                                                        {user.profile.address.state === "CA" ? (
                                                                          <p>Free shipping available!</p>
                                                                        ) : (
                                                                          <p>Shipping: $29.99</p>
                                                                        )}
                                                                      </div>
                                                                    ) : (
                                                                      <p>Please add state for shipping estimate</p>
                                                                    )}
                                                                  </div>
                                                                ) : (
                                                                  <p>International shipping available</p>
                                                                )}
                                                              </div>
                                                            ) : (
                                                              <p>Add address for shipping options</p>
                                                            )}
                                                          </div>
                                                        ) : (
                                                          <p>Complete your profile</p>
                                                        )}
                                                      </div>
                                                    ) : (
                                                      <div className="standard-product">
                                                        <h3>{product.name}</h3>
                                                        <p>Price: ${product.price}</p>
                                                      </div>
                                                    )}
                                                  </div>
                                                ) : (
                                                  <div>
                                                    {product.category === "clothing" ? (
                                                      <div className="clothing-item">
                                                        <h3>{product.name}</h3>
                                                        <p>In Stock: {product.stock}</p>
                                                      </div>
                                                    ) : (
                                                      <div className="other-item">
                                                        <h3>{product.name}</h3>
                                                      </div>
                                                    )}
                                                  </div>
                                                )}
                                              </div>
                                            ) : (
                                              <div className="out-of-stock">
                                                <h3>{product.name}</h3>
                                                <p>Currently Out of Stock</p>
                                              </div>
                                            )}
                                          </div>
                                        ))}
                                      </div>
                                    ) : (
                                      <p>No products available</p>
                                    )}
                                  </div>
                                </div>
                              ) : (
                                <div>
                                  {user.permissions.includes("editor") ? (
                                    <div className="editor-panel">
                                      <h2>Editor Controls</h2>
                                      <p>Limited access</p>
                                    </div>
                                  ) : (
                                    <div className="user-panel">
                                      <h2>User Dashboard</h2>
                                      <p>Basic access</p>
                                    </div>
                                  )}
                                </div>
                              )}
                            </div>
                          ) : (
                            <div>
                              {user.subscription.tier === "basic" ? (
                                <div>
                                  <h1>Welcome Basic Member: {user.name}</h1>
                                  <p>Upgrade to premium for more features!</p>
                                </div>
                              ) : (
                                <div>
                                  <h1>Welcome {user.name}</h1>
                                </div>
                              )}
                            </div>
                          )}
                        </div>
                      ) : (
                        <div>
                          <h1>Subscription Expired</h1>
                          <p>Please renew your subscription</p>
                        </div>
                      )}
                    </div>
                  ) : (
                    <div>
                      <h1>Welcome {user.name}</h1>
                      <p>Subscribe to access premium features</p>
                    </div>
                  )}
                </div>
              ) : (
                <div>
                  <h1>Please log in</h1>
                  <button>Login</button>
                </div>
              )}
            </div>
          )}
        </div>
      )}
    </div>
  );
};
